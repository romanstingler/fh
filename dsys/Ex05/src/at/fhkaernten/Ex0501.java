package at.fhkaernten;
public class Ex0501 {
	// 5.1
	// The happened-before relation is a relation between the result of two events. 
	// if one event happens before another event, the result must reflect that. 
	// This involves ordering events based on the potential causal
	// relationship of pairs of events in a concurrent system, especially
	// asynchronous distributed systems.
	// It must be guaranteed that event A finishes writing into the memory, 
	// which must be visible by event B, before B starts reading it.

	// 5.2
	// A system provides causal consistency if memory operations that
	// are causally related are seen by every node of the system in
	// the same order.
	//
	// When a node performs a read followed later by a write, even on a
	// different variable, the first operation is said to be causally ordered
	// before the second, because the value stored by the write may have been
	// dependent upon the result of the read. Similarly, a read operation is
	// causally ordered after the earlier write on the same variable that stored
	// the data retrieved by the read. Also, even two write operations performed
	// by the same node are defined to be causally ordered, in the order they
	// were performed. Intuitively, after writing value v into variable x, a
	// node knows that a read of x would give v, so a later write could be said
	// to be causally related to the earlier one. Finally, we
	// force this causal order to be transitive: that is, we say that if
	// operation A is ordered before B, and B is ordered before C, A
	// is ordered before C.


	// 5.3
	// Vector Timestamps have the advantage in comparison to Lamport Timestamps
	// that Lamport timestamps does not guarantee if e1 has a smaller time stamp than e2 that it happened before
	// because concurrent events are ordered arbitrarily.
	// To detect causality violation, a VT is needed with must contain information about the other processors.


	// 5.4 
	// (A)
	// The two vectors are compared component-by-component e.g. [1,2,3] and [2,4,3] so (1 with 2 , 2 with 4 and 3 with 3).
	//
	// (B)
	// The vector components are set to the higher value, additionally the local time is increased by 1.
	//
	// (C)
	// If two events are concurrent no path between e1 and e2 are allowed in the directed acyclic graph.

}
