package algolizer;

import processing.core.PApplet;

class Display extends PApplet {
    enum GameState {
        MENU, RUNNING, SORTED
    }

    private GameState state = GameState.MENU;
    private AbsSorter sorter;
    private int sorterIdx = 0;

    private final int bgColor = color(28, 31, 51);
    private final int fgColor = color(211, 62, 67);
    private final int white = color(255, 255, 255);

    // Window size
    private final int width = 700;
    private final int height = 600;

    private final int frameRate = 10;

    // Bar settings
    private final int BAR_HEIGHT_FACTOR = 20;
    private final int BAR_WIDTH = 30;
    private int BAR_X_START;
    private final int BAR_X_OFFSET = 15;
    private final int NUMBER_Y_OFFSET = 20;

    @Override
    public void settings() {
        size(width, height);
        smooth();
    }

    @Override
    public void setup() {
        frameRate(frameRate);
        background(bgColor);
        textAlign(CENTER, CENTER);
        textSize(15);
    }

    @Override
    public void draw() {
        switch (state) {
            case MENU -> drawMenu();
            case RUNNING -> drawRunning();
            case SORTED -> drawSorted();
        }
    }

    @Override
    public void keyPressed() {
        if (state == GameState.MENU) {
            switch (keyCode) {
                case UP -> sorterIdx = Math.max(0, sorterIdx - 1);
                case DOWN -> sorterIdx = Math.min(AbsSorter.Algorithms.values().length - 1, sorterIdx + 1);
                case 32 -> initGame(); // Space
            }
        } else if (state == GameState.SORTED && keyCode == 32) {
            state = GameState.MENU;
            loop();
        }
    }

    private void initGame() {
        background(bgColor);
        int[] numbers = new int[]{8, 3, 14, 1, 28, 5, 22, 2, 17, 7, 35, 11, 20, 3};
        sorter = AbsSorter.create(AbsSorter.Algorithms.values()[sorterIdx], numbers);
        BAR_X_START = (width - sorter.numbers.length * (BAR_WIDTH + BAR_X_OFFSET)) / 2;
        state = GameState.RUNNING;
    }

    private void drawMenu() {
        background(bgColor);
        AbsSorter.Algorithms[] algorithms = AbsSorter.Algorithms.values();

        fill(white);
        textSize(24);
        text("Algolizer", (float) width / 2, height * 0.1f);

        textSize(15);
        text("select with <space>", (float) width / 2, height * 0.16f);


        int RECT_WIDTH = 250;
        int RECT_HEIGHT = 100;

        int RECT_X_FROM = (width - RECT_WIDTH) / 2;
        int RECT_Y_OFFSET = 20;
        int RECT_Y_FROM = (height - algorithms.length * (RECT_HEIGHT + RECT_Y_OFFSET)) / 2 + 40;

        float fromY = 0;

        for (int i = 0; i < algorithms.length; i++) {
            AbsSorter.Algorithms algorithm = algorithms[i];
            fill(fgColor);
            fromY = RECT_Y_FROM + i * (RECT_HEIGHT + RECT_Y_OFFSET);

            stroke(i == sorterIdx ? white : fgColor);
            rect(RECT_X_FROM, fromY, RECT_WIDTH, RECT_HEIGHT);

            fill(white);
            text(algorithm.name(), (float) width / 2, (2 * fromY + RECT_HEIGHT) / 2);
        }

    }

    private void drawRunning() {
        background(bgColor);
        stroke(fgColor);

        text(AbsSorter.Algorithms.values()[sorterIdx].name(), (float) width / 2, height * 0.05f);

        if (sorter.isSorted())
            state = GameState.SORTED;

        if (frameCount % frameRate == 0) {
            sorter.step();
        }

        for (int i = 0; i < sorter.numbers.length; i++) {
            float barHeight = BAR_HEIGHT_FACTOR * sorter.weights.get(sorter.numbers[i]);
            float fromX = BAR_X_START + i * (BAR_X_OFFSET + BAR_WIDTH);
            fill(fgColor);
            rect(fromX, height, BAR_WIDTH, -barHeight);

            float textCenter = (2 * fromX + BAR_WIDTH) / 2;
            fill(white);
            text(sorter.numbers[i], textCenter, height - barHeight - NUMBER_Y_OFFSET);
        }
    }

    private void drawSorted() {
        fill(color(white));
        text("Sorted! Press <space> to quit", (float) width / 2, height * 0.09f);
        noLoop();
    }
}