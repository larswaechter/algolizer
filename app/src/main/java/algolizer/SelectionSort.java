package algolizer;

class SelectionSort extends AbsSorter {
    int lastSwitchIdx = 0;

    SelectionSort(int[] arr) {
        super(arr);
    }

    @Override
    public void step() {
        int min = lastSwitchIdx;
        for (int i = lastSwitchIdx + 1; i < arr.length; i++)
            if(arr[i] < arr[min]) min = i;

        int tmp = arr[lastSwitchIdx];
        arr[lastSwitchIdx] = arr[min];
        arr[min] = tmp;
        lastSwitchIdx++;
    }
}
