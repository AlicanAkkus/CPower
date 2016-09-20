# CPower
CPower, provide a memory statistic for your applications. 

CPower can be print memory details with your selected interval. 

How use it?

```java
   CPower cPower = CPower.getInstance(); // get CPower instance
   cPower.initialize(10); // initialize cpower with 10 second delay. After initialization CPower can be pring memory details to console output.
   cPower.stopCPower(); // you can stop service when you want.
   
   //Output can be as below;
    23:41:18.317 [Thread-1] INFO  com.cpower.base.CPower - 
	Max Memory : 1753088 kb.
	Total Memory : 119808 kb.
	Used Memory : 27132 kb.
	Free Memory : 92676 kb.
```	
