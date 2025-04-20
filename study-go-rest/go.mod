module com/br/simple-go-mod

// Command to install new dependencies: go get -u
go 1.24.2

require (
	github.com/gorilla/mux v1.8.1 // helps using routing -- indirect
	github.com/joho/godotenv v1.5.1 // helps with env vars -- indirect
	github.com/lib/pq v1.10.9 // connection with postgres -- indirect
)
