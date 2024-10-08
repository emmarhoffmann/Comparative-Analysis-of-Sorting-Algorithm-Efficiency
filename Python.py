import csv
import time
import copy

# Sorting algorithms implementations
def selection_sort(arr):
    for i in range(len(arr)):
        min_idx = i
        for j in range(i+1, len(arr)):
            if arr[j] < arr[min_idx]:
                min_idx = j
        arr[i], arr[min_idx] = arr[min_idx], arr[i]
    return arr

def bubble_sort(arr):
    n = len(arr)
    for i in range(n):
        for j in range(0, n-i-1):
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]
    return arr

def insertion_sort(arr):
    for i in range(1, len(arr)):
        key = arr[i]
        j = i-1
        while j >=0 and key < arr[j]:
            arr[j+1] = arr[j]
            j -= 1
        arr[j+1] = key
    return arr

def merge_sort(arr):
    if len(arr) > 1:
        mid = len(arr)//2
        L = arr[:mid]
        R = arr[mid:]

        merge_sort(L)
        merge_sort(R)

        i = j = k = 0

        while i < len(L) and j < len(R):
            if L[i] < R[j]:
                arr[k] = L[i]
                i += 1
            else:
                arr[k] = R[j]
                j += 1
            k += 1

        while i < len(L):
            arr[k] = L[i]
            i += 1
            k += 1

        while j < len(R):
            arr[k] = R[j]
            j += 1
            k += 1
    return arr

def quick_sort(arr):
    if len(arr) <= 1:
        return arr
    else:
        pivot = arr[0]
        less = [x for x in arr[1:] if x <= pivot]
        greater = [x for x in arr[1:] if x > pivot]
        return quick_sort(less) + [pivot] + quick_sort(greater)



# Function to read CSV data
def read_csv(file_name):
    with open(file_name, 'r') as file:
        reader = csv.reader(file)
        for row in reader:
            return [int(x) for x in row]

# Function to time a sorting algorithm
def time_sort(sort_function, data):
    start_time = time.time()
    sort_function(data)
    end_time = time.time()
    return end_time - start_time

# Function to average runtime over multiple runs
def average_runtime(sort_function, data, runs):
    total_duration = 0
    for _ in range(runs):
        data_copy = copy.deepcopy(data)  # Deep copy to avoid sorting already sorted data
        total_duration += time_sort(sort_function, data_copy)
    return total_duration / runs

# Main section
if __name__ == "__main__":
    file_name = '1000_Random_Numbers.csv'  # Update this with your CSV file path
    data = read_csv(file_name)
    num_runs = 1000  # You can change this to your desired number of runs

    # List of sorting functions
    sorting_algorithms = {
        'Selection Sort': selection_sort,
        'Bubble Sort': bubble_sort,
        'Insertion Sort': insertion_sort,
        'Merge Sort': merge_sort,
        'Quick Sort': quick_sort
    }

    # Apply each sorting algorithm and print average runtimes in milliseconds
    for name, sort_function in sorting_algorithms.items():
        avg_duration = average_runtime(sort_function, data, num_runs)
        avg_duration_ms = avg_duration * 1000  # Convert seconds to milliseconds
        print(f"{name} average duration over {num_runs} runs: {avg_duration_ms:.2f} milliseconds.")
