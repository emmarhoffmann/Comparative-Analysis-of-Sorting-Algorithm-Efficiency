#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#include <chrono>
#include <functional>

// Utility functions to split string and convert to integer
std::vector<int> splitAndConvertToInt(const std::string& str, char delimiter) {
    std::vector<int> result;
    std::stringstream ss(str);
    std::string token;
    while (std::getline(ss, token, delimiter)) {
        result.push_back(std::stoi(token));
    }
    return result;
}

// Function to read CSV data
std::vector<int> readCsv(const std::string& fileName) {
    std::ifstream file(fileName);
    std::string line;
    if (file.good() && std::getline(file, line)) {
        return splitAndConvertToInt(line, ',');
    }
    return {};
}

// Function to time a sorting algorithm
double timeSort(std::function<void(std::vector<int>&)> sortFunc, std::vector<int> data) {
    auto start = std::chrono::high_resolution_clock::now();
    sortFunc(data);
    auto end = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> diff = end - start;
    return diff.count();
}

// Sorting algorithms implementations
void selectionSort(std::vector<int>& arr) {
    for (size_t i = 0; i < arr.size(); i++) {
        size_t min_idx = i;
        for (size_t j = i + 1; j < arr.size(); j++) {
            if (arr[j] < arr[min_idx]) {
                min_idx = j;
            }
        }
        std::swap(arr[i], arr[min_idx]);
    }
}

void bubbleSort(std::vector<int>& arr) {
    size_t n = arr.size();
    for (size_t i = 0; i < n - 1; i++) {
        for (size_t j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                std::swap(arr[j], arr[j + 1]);
            }
        }
    }
}

void insertionSort(std::vector<int>& arr) {
    for (size_t i = 1; i < arr.size(); i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

void merge(std::vector<int>& arr, size_t l, size_t m, size_t r) {
    size_t n1 = m - l + 1;
    size_t n2 = r - m;

    std::vector<int> L(n1), R(n2);

    for (size_t i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (size_t j = 0; j < n2; j++)
        R[j] = arr[m + 1 + j];

    size_t i = 0;
    size_t j = 0;
    size_t k = l;

    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

void mergeSortRecursive(std::vector<int>& arr, size_t l, size_t r) {
    if (l >= r) {
        return;
    }
    size_t m = l + (r - l) / 2;
    mergeSortRecursive(arr, l, m);
    mergeSortRecursive(arr, m + 1, r);
    merge(arr, l, m, r);
}

void mergeSort(std::vector<int>& arr) {
    mergeSortRecursive(arr, 0, arr.size() - 1);
}

void quickSortRecursive(std::vector<int>& arr, int low, int high) {
    if (low < high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                std::swap(arr[i], arr[j]);
            }
        }
        std::swap(arr[i + 1], arr[high]);

        int pi = i + 1;
        quickSortRecursive(arr, low, pi - 1);
        quickSortRecursive(arr, pi + 1, high);
    }
}

void quickSort(std::vector<int>& arr) {
    quickSortRecursive(arr, 0, arr.size() - 1);
}

// Main function
int main() {
    std::string fileName = "100000_Random_Numbers.csv"; // Update with your CSV file path
    auto data = readCsv(fileName);
    const int numRuns = 10; // Number of runs for averaging

    std::vector<std::pair<std::string, std::function<void(std::vector<int>&)>>> sortingAlgorithms = {
        {"Selection Sort", selectionSort},
        {"Bubble Sort", bubbleSort},
        {"Insertion Sort", insertionSort},
        {"Merge Sort", mergeSort},
        {"Quick Sort", quickSort}
    };

    for (const auto& [name, func] : sortingAlgorithms) {
        double totalDuration = 0.0;
        for (int i = 0; i < numRuns; ++i) {
            std::vector<int> dataCopy = data; // Make a copy of the data for each run
            totalDuration += timeSort(func, dataCopy);
        }
        double avgDurationMs = (totalDuration / numRuns) * 1000.0; // Convert to milliseconds
        std::cout << name << " average duration over " << numRuns << " runs: "
                  << avgDurationMs << " milliseconds." << std::endl;
    }

    return 0;
}
