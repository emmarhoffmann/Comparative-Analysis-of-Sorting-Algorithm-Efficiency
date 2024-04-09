import csv
import random

# Parameters
num_elements = 100000
range_min = -1000
range_max = 1000
output_file = '100000_Random_Numbers.csv'

# Generating a list of X random numbers between -1000 and 1000
random_numbers = [random.randint(range_min, range_max) for _ in range(num_elements)]

# Writing the list to a CSV file
with open(output_file, 'w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(random_numbers)  # Writing as a single row

print(f"CSV file '{output_file}' with {num_elements} random numbers created.")
