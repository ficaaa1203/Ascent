# changexCoord / changeYCoord

Moves `xCoord` / `yCoord` for about 5 seconds using a `Timer`.

## How it works

- `Timer` calls `run()` on a background thread at a fixed period.
- Each call adds a step to the coordinate.
- `count` is a field of the `TimerTask`, so it persists between calls. It decrements every tick, and when it drops below 0 the timer cancels itself.

## Why the original was choppy

Period was 1000 ms with a step of `value`, so the object jumped `value` pixels once per second.

## Smoother version

```java
int count = 250;
xCoord += value / 50.0;
timer.scheduleAtFixedRate(task, 0, 20);
```

- Period 1000 ms -> 20 ms: 50 updates per second.
- Step `value` -> `value / 50.0`: each tick moves 1/50 of the old step, so the speed in pixels per second is unchanged.
- `count` 5 -> 250: 250 ticks x 20 ms = 5 seconds.

## Notes

- `xCoord` / `yCoord` must be `double` or `float`. With `int`, `value / 50.0` won't compile and an integer `value / 50` rounds small values to 0.
- The check is `count < 0` after the decrement, so the task runs `count + 1` times. Original: 6 runs, total `6 * value`. Smooth version: 251 runs, total about `5.02 * value`. For the exact original total use `value * 6 / 250.0`.

## `count` as a parameter

```java
public void changexCoord(int value, int count) {
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        int aux = count * 50;
        public void run() {
            aux--;
            xCoord += value / 50.0;
            if(aux < 0){
                timer.cancel();
            }
        }
    };
    timer.scheduleAtFixedRate(task, 0, 20);
}
```

`changeyCoord` is identical, with `yCoord`.

- `count` is the duration in **seconds**. `value` is the distance per second. Total distance is about `value * count`.
- `aux = count * 50`: 50 ticks per second (20 ms period) x `count` seconds.
- The cancel check uses `aux`, not `count`. `count` never changes, so checking it would never stop the timer.
- Example: `changexCoord(100, 5)` moves x by about 100 per second for 5 seconds.

https://www.youtube.com/watch?v=IwC5T5gtFI4
