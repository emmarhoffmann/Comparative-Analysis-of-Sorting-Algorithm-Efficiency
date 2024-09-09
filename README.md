# Comparative Analysis of Sorting Algorithm Efficiency

## About
This project investigates the efficiency of five sorting algorithms — Selection Sort, Bubble Sort, Insertion Sort, Merge Sort, and Quick Sort — across three different programming languages: **C++**, **Java**, and **Python**. The study evaluates how algorithm performance varies depending on language constructs and runtime environments, using datasets of different sizes to assess execution times. The results offer insights into the optimal selection of sorting algorithms and programming languages for data-intensive applications.

## Features
- **Multi-language Comparison**: Implements sorting algorithms in C++, Java, and Python for performance comparison.
- **Execution Timing**: Uses language-specific timing functions to precisely measure execution times for each algorithm.
- **Data Analysis**: Assesses the performance of each algorithm across datasets of 1,000, 10,000, and 100,000 elements.
- **Random Number Generator**: Python script to generate randomized datasets for consistent testing of algorithms across languages.

## Results and Analysis
- **C++ Performance**:
  - Quick Sort consistently performed the best in C++, handling large datasets efficiently. It processed 100,000 elements in **14.13 ms**, while Merge Sort followed closely with **97.31 ms**.
  - Bubble Sort and Selection Sort showed significantly higher execution times, particularly for large datasets, due to their **O(n²)** time complexity.
  
- **Java Performance**:
  - In Java, both Quick Sort and Merge Sort also excelled, with Quick Sort completing 100,000 elements in **18.59 ms** and Merge Sort in **25.68 ms**.
  - The Java runtime environment contributed to slightly higher execution times compared to C++, but Java's garbage collection mechanisms optimized memory management, allowing Merge Sort to outperform C++ in some cases.
  
- **Python Performance**:
  - As an interpreted language, Python exhibited slower performance across all algorithms. Quick Sort took **245.42 ms** for 100,000 elements, which is significantly slower than the compiled languages.
  - Despite this, the relative efficiency of Merge Sort and Quick Sort remained evident in Python, showing their inherent scalability despite Python's slower execution times.

### Comparative Insights
- **Quick Sort** emerged as the most consistently efficient algorithm across all three languages, particularly for large datasets, due to its divide-and-conquer approach.
- **Merge Sort** performed second-best overall, especially in handling larger datasets due to its **O(n log n)** time complexity and stable sorting nature.
- **Selection Sort** and **Bubble Sort** underperformed, especially as dataset sizes increased, reaffirming their inefficiency for large-scale data.

## Project Structure
- `C++.cpp`: Implements the sorting algorithms in C++ and includes code for measuring execution time.
- `Java.java`: Java implementation of the sorting algorithms with system time-based execution measurement.
- `Python.py`: Python script implementing the sorting algorithms and tracking execution time.
- `RandNumGenerator.py`: Generates random numbers for testing, producing CSV files with datasets of varying sizes.
- `1000_Random_Numbers.csv`, `10000_Random_Numbers.csv`, `100000_Random_Numbers.csv`: Datasets used for sorting, containing random numbers within the range of -1000 to 1000.

## Methodology
- **Timing Functions**: 
   - C++: `std::chrono`
   - Java: `System.nanoTime()`
   - Python: `time.time()`
- **Testing**: Each algorithm was tested on datasets of 1,000, 10,000, and 100,000 elements. The results for each trial were averaged over 10 runs to ensure consistency in performance measurements.

## Future Improvements
- Expand Algorithm Variety: Incorporate additional sorting algorithms such as Heap Sort and Radix Sort for a more comprehensive analysis.
- Increase Dataset Sizes: Test the algorithms on larger datasets to explore how performance scales with even more data.
- Leverage New Language Features: Explore new optimization techniques and language features in C++, Java, and Python to improve sorting efficiency.
- Analyze More Programming Languages: Include modern languages like Go, Rust, or Swift for broader performance comparison.
- Visualize Results: Develop graphs or charts to better illustrate performance differences across algorithms and languages.

