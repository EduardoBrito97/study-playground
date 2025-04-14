package main

import (
	config "com/br/simple-go-mod/Config"
	"log"
	"net/http"
)

func main() {
	dbConnection := config.DbSetup()

	// Whenever main stops, dbConnection.Close() is called
	defer dbConnection.Close()

	log.Fatal(http.ListenAndServe(":8080", nil))
}
