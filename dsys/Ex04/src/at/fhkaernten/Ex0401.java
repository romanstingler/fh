package at.fhkaernten;

public class Ex0401 {

	// It can be seen that 'Number' is incremented on the server and on
	// the client, 'objNumber' is just incremented on the server.
	// This is due to the fact that number is static.


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
	// Server - Hello Test
	// Number: 11 objNumber: 11
	// Number: 12 objNumber: 12
	// Number: 13 objNumber: 13
	// Number: 14 objNumber: 14
	// Number: 15 objNumber: 15
	// Number: 16 objNumber: 16
	// Number: 17 objNumber: 17
	// Number: 18 objNumber: 18
	// Number: 19 objNumber: 19
	// Number: 20 objNumber: 20

	// Client - Hello Test
	// Client - Number: 1 objNumber: 0
	// Client - Number: 2 objNumber: 0
	// Client - Number: 3 objNumber: 0
	// Client - Number: 4 objNumber: 0
	// Client - Number: 5 objNumber: 0
	// Client - Number: 6 objNumber: 0
	// Client - Number: 7 objNumber: 0
	// Client - Number: 8 objNumber: 0
	// Client - Number: 9 objNumber: 0
	// Client - Number: 10 objNumber: 0
	// Client - Hello Test
	// Client - Number: 11 objNumber: 0
	// Client - Number: 12 objNumber: 0
	// Client - Number: 13 objNumber: 0
	// Client - Number: 14 objNumber: 0
	// Client - Number: 15 objNumber: 0
	// Client - Number: 16 objNumber: 0
	// Client - Number: 17 objNumber: 0
	// Client - Number: 18 objNumber: 0
	// Client - Number: 19 objNumber: 0
	// Client - Number: 20 objNumber: 0

	// Serialization is the process of converting an object's state to a
	// sequence of bytes. Serialization is used when you want to
	// persist an object. It is also used by RMI to pass objects between JVMs,
	// either as arguments in a method invocation from a client to a server or
	// as return values from a method invocation. In general, serialization is
	// used when we want the object to exist beyond the lifetime of the JVM.

}
