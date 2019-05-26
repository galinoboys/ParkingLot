This a Parking Lot System written in Java

The test cases are run my a main class ParkingLotTester.java.

While initiating the project the we have to give four parameters
noOfFloors : Total no of floors in the system (max : 50)
length and width : Which will represent matrix size of each floor. max (1000 each)
noOfGates : This will tell how many gates are there to enter any floor of the parking lot (max : 4)

GateNo1 : NorthSideGate
GateNo2 : EastSideGate
GateNo3 : SouthSideGate
GateNo4 : WestSideGate

if we have a floor with 3*4 matrix and 4 gates then this will be the representation of gates

                 (N)
            1   2   3   4
        (W) 5   6   7   8 (E)
            9  10  11  12
                 (S)

If vehicles enters from the South Gate (Gate no 3) : this is the order of assignment of slots (10,11,9,6,7,12,5,2,3,8,1,4)

The application is made thread-safe to avoid concurrent updates in data.
All the data is in memory,