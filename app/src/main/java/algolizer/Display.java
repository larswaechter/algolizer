package algolizer;

import processing.core.PApplet;

class Display extends PApplet {
    enum GameState {
        RUNNING, SORTED
    }

    private GameState state = GameState.RUNNING;
    private final AbsSorter sorter = new InsertionSort(new int[]{8, 3, 14, 1, 28, 5, 22, 2, 17, 7, 35, 11, 20, 3});

    private final int bgColor = color(28, 31, 51);
    private final int fgColor = color(211, 62, 67);

    // Window size
    private final int width = 700;
    private final int height = 600;

    private final int frameRate = 30;
    private final int sortSpeedSec = 2;

    // Bar settings
    private final int BAR_HEIGHT_FACTOR = 20;
    private final int BAR_WIDTH = 30;
    private final int BAR_X_OFFSET = 15;
    private final int BAR_X_START = (width - sorter.numbers.length * (BAR_WIDTH + BAR_X_OFFSET)) / 2;
    private final int NUMBER_Y_OFFSET = 15;

    @Override
    public void settings() {
        size(width, height);
    }

    @Override
    public void setup() {
        frameRate(frameRate);
        background(bgColor);
        textAlign(CENTER);
        textFont(createFont("Arial", 20));
    }

    @Override
    public void draw() {
        if (sorter.isSorted())
            state = GameState.SORTED;

        switch (state) {
            case RUNNING -> drawBars();
            case SORTED -> drawSorted();
        }
    }

    private void drawBars() {
        fill(fgColor);

        if (frameCount % (sortSpeedSec * frameRate) == 0) {
            background(bgColor);
            sorter.step();
        }

        for (int i = 0; i < sorter.numbers.length; i++) {
            float barHeight = BAR_HEIGHT_FACTOR * sorter.weights.get(sorter.numbers[i]);
            float barFrom = BAR_X_START + i * (BAR_X_OFFSET + BAR_WIDTH);
            rect(barFrom, height, BAR_WIDTH, -barHeight);

            float textCenter = (2 * barFrom + BAR_WIDTH) / 2;
            textSize(14);
            text(sorter.numbers[i], textCenter, height - barHeight - NUMBER_Y_OFFSET);
        }
    }

    private void drawSorted() {
        textSize(20);
        fill(color(fgColor));
        text("Sorted!", (float) this.width / 2, height * 0.10f);
        noLoop();
    }
}
