# Uni-Project-2nd-Year
Project Specifications
This project investigates repetitions in genomic sequences. A genomic sequence, for the purposes of this
project, is considered as a string of symbols over the alphabet {A, C, G, T}. Genomic sequences can
contain both tandem repeats and interspersed repeats, and these play an important role in the DNA
analysis of individuals. In this project, you are required to construct and analyse long genomic sequences
for which no repeats of the form xyx occurs, where x is any single alphabet symbol, and y is a sequence
of any length over the alphabet. That is, repetitions can have any length from two (when y is empty)
up to one less than the length of the input.
As with many other bio-informatics tasks, the problem is both space and time intensive – just the right
kind of project for this course

Mode 1: Checker
Mode 1 focuses on checking whether a given string contains any repetitions. The aim is to develop the
mechanism to take extremely long strings, and step through them. For each step, find the current string
xyx, and check whether it occurs anywhere else in the long string. Be careful! There can be more than
one string xyx, starting at the index of x. For example, if you start at index 0 in the string ACTGAACGTAA,
you have the symbol A. From that index, there are four strings of the form xyx, namely, ACTGA, ACTGAA,
ACTGAACGTA, and ACTGAACGTAA. You must test for repetitions of any of those. Try to conjure up some
Page 4
theory – clearly, the repeating string cannot be as long as the input string itself. It could, however, have
length one less than the input string, as in AAAAA, where there are two occurrences of AAAA. And so on.
The straightforward implementation of the checker is quite easy, but it could be really slow. You have to
think hard in this phase about the data structures, objects and algorithms that you can use to optimise
space and time issues. Your solution will be penalised if you run out of memory or if the algorithm takes
too long to execute.
Hint: see if you can find some ideas in the string search algorithms of chapter 5 in the textbook.
Input
java Repeats 1 <filename>

Mode 2: Brute force generator
Mode 2 requires you to actually generate (hopefully long) genomic sequences with the required nonrepetition property. This is an enumeration task, as you will be expected to generate the whole tree, but
chop off branches that would cause a repetition if they grow longer. You should generate all branches
up to the point of repetition, and output the full branch with its length. Incidentally, many game trees
work in a similar way, where all branches are considered, and non-preferred branches are pruned.
  
  Mode 3: Optimisation
Mode 3 measures your careful design of your data structures and algorithms. We know that the problem
explodes, and so mode 3 will require you to find the longest possible non-repetitive genomic sequence in
the shortest possible time. In mode 2 you traversed the full tree, and had to output every single branch.
In mode 3, we require simply that your program keeps track of the lengths as it traverses the tree, and
remember only the longest valid string and its length
