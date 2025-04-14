package handlers

import (
	"database/sql"
	"log"

	"github.com/gorilla/mux"
)

func HandleRouters(db *sql.DB) *mux.Router {
	router := mux.NewRouter()

	taskHandler := NewTaskHandler(db)
	router.HandleFunc("/tasks", taskHandler.ReadTasks).Methods("GET")
	router.HandleFunc("/tasks", taskHandler.CreateTask).Methods("POST")
	router.HandleFunc("/tasks/{id}", taskHandler.UpdateTask).Methods("PUT")
	router.HandleFunc("/tasks/{id}", taskHandler.DeleteTask).Methods("DELETE")

	log.Println("Routers initialized successfully")

	return router
}
