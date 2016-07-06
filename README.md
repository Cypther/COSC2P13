# COSC2P13 Computer Systems

SPOOLer Simulation Project 

Queue Algorithms 

FIFO: The printouts are produced in the order of the arrival time. 

SPJF: Shortest print job first. The approach of this method is print queue is sorted in ascending order of by the numbers of pages.

PAPQ: Priority-aged print queue. The print queue is sorted in descending order of print job priority. How the priority is calculated, P = priority, A and B are carefully chosen constants. B multiply by T. Where T is time.  The following equation looks like P = A + B*T

LBAQ: Length-based aged queue: Similar to PAPQ. The print queue is sorted  n descending order of  print job priority. The priority is calculated as P = (A + B*T)/L, where L is the numbers of pages.


•	How the simulation was conducted, we assumed the printer is capable of printing 40 pages a minute.  The average print job is 4 pages long, using Poisson distribution.

•	The java code to generate the arrival time in the program looks as the following. Using Poisson distribution that simulates the inter-event arrival time, we want it to be exponential distribution.
