package foo

import "fmt"

var TestBar int = 10
var lowerCaseBar int = 9

// The function has capital B in order to expose it
func Bar() {
	barPrivate()
}

func barPrivate() {
	fmt.Println("bar")
}
