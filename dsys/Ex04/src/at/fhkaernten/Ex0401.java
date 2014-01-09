package at.fhkaernten;

public class Ex0401 {

	// Files: Client.java, Server.java, Hello.java, HelloImpl.java,
	// MessageObject.java

	// It can be seen that 'objNumber' is incremented on the server and on
	// the client, 'Number' is just incremented on the server.
	// This is due to the fact that number is static. Static variables are not
	// shared between JVMs by RMI. Thus the line this.objNumber = ++number; in
	// the constructor of the MessageObject only increases the number variable
	// on the server side.

	// Output Server:
	// Registry created
	// Server - Hello Test
	// Number: 1 objNumber: 1
	// Number: 2 objNumber: 2
	// Number: 3 objNumber: 3
	// Number: 4 objNumber: 4
	// Number: 5 objNumber: 5
	// Number: 6 objNumber: 6
	// Number: 7 objNumber: 7
	// Number: 8 objNumber: 8
	// Number: 9 objNumber: 9
	// Number: 10 objNumber: 10

	// Output Client:
	// Client - Hello Test
	// Client - Number: 0 objNumber: 1
	// Client - Number: 0 objNumber: 2
	// Client - Number: 0 objNumber: 3
	// Client - Number: 0 objNumber: 4
	// Client - Number: 0 objNumber: 5
	// Client - Number: 0 objNumber: 6
	// Client - Number: 0 objNumber: 7
	// Client - Number: 0 objNumber: 8
	// Client - Number: 0 objNumber: 9

	// Serialization is the process of converting an object's state to a
	// sequence of bytes. Serialization is used when you want to
	// persist an object. It is also used by RMI to pass objects between JVMs,
	// either as arguments in a method invocation from a client to a server or
	// as return values from a method invocation. In general, serialization is
	// used when we want the object to exist beyond the lifetime of the JVM.

}
