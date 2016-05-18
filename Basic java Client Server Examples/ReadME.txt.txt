In this Project different Java Client Server Interaction is showed 
using an example of a calculator.

The examples are below

1. A Stateful Server That calculates a running total.
	eg. java SockClient1 100 - 100
		java SockClient1 500 - 600
		
2. Allows the Client to send a "reset" to set the running total to 0

3. Stateful server on a per client bases. Meaning a serparate Running
	total per Client.
	
4. State Persistance by puting the serverside data into an XML file

5. An example of a Multi Threaded server. Keeping the Thread-safety in mind.