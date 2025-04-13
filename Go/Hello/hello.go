package main

// To import custom packages, we follow the name set on the go.mod file + path
import (
	"example/hello/foo"
	"fmt"
	"strconv"
)

// Every go application must have a main method -- libraries doesn't need to
// Also need to do go mod init /example/package in the beginning so it constructs the go.mod file correctly
func main() {
	var nome bool
	// Default value for bools are false -- not null
	fmt.Println("Hello, World! " + strconv.FormatBool(nome))

	// We can directly use functions from other files if its in the same dir
	teste()

	// This also happens with variables
	fmt.Println(mainPackageVar)

	// To use functions from other paths, tho, we need it initialized by capital letters
	foo.Bar()

	// And ofc also happens with variables
	fmt.Println(foo.TestBar)
	// fmt.Println(foo.lowerCaseBar) wont work cos lowerCaseBar doesnt start with capital letter

	pointerTest()
}

func pointerTest() {
	var testPointer *int
	// Gonna be nil cos we didn't attach any address to it
	fmt.Println(testPointer)

	// The new func allocates an address, and tells its gonna be an int
	testPointer = new(int)
	fmt.Println(testPointer)

	// The content is 0 because its the default value of ints
	fmt.Println(*testPointer)

	// And we can set it similar to C, by using *
	*testPointer = 9
	fmt.Println(*testPointer)

	// it also works for normal variables:
	var testNumber int = 10
	testPointer = &testNumber
	*testPointer = 99

	// Its gonna be 99 since we changed it beforehanded
	fmt.Println(testNumber)

	// We can use blank identifiers!
	var _, two = twoVariablesFunc()
	fmt.Println(two)
}

// Functions that are not void must be specified returns
// Its also possible to include two values instead of 1
func twoVariablesFunc() (int, int) {
	return 1, 2
}
