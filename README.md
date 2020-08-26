# Parallel-Computing-Pi-Calculation
Pi calculator in Java using multithreading techniques.
Pi calculator in Java using multithreading techniques.

## How to use
Execute the pi.jar using the command: 

	```
	java -jar pi.jar
	```
	
**Optional parameters**:
* *-p 1000* or *--terms 1000*   <br />~ Sets the number of terms that it has to calculate. Ex. 1000 
* *-t 4*  or *--threads 4*      <br />~ Sets the number of thread to be used in the calculation. Ex 4
* *-o pi.txt* or *--out pi.tx* 	<br />~ Sets an output file to the calculated Pi
* *-q* or *--quiet*            <br />	~ Puts the program in quiet mode. The ui will not be started and the return value will be the time needed to compute the Pi. The Pi itself will go in the file specified using '-o'

## Resources
1. Pi calculation - using Ramanujan's Formula for Pi
2. UI - using Swing
