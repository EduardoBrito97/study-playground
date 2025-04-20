package handlers

import (
	"com/br/simple-go-mod/models"
	"database/sql"
	"encoding/json"
	"net/http"

	"strconv"

	"github.com/gorilla/mux"
)

type TaskHandler struct {
	db *sql.DB
}

// TaskHandler ctr
func NewTaskHandler(db *sql.DB) *TaskHandler {
	return &TaskHandler{db: db}
}

func (taskHandler *TaskHandler) ReadTasks(writer http.ResponseWriter, request *http.Request) {
	var tasks []models.Task

	rows, err := taskHandler.db.Query("SELECT * FROM tasks")
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		return
	}

	for rows.Next() {
		var task models.Task
		err := rows.Scan(&task.ID, &task.Title, &task.Description, &task.Status)
		if err != nil {
			http.Error(writer, err.Error(), http.StatusInternalServerError)
			return
		}

		tasks = append(tasks, task)
	}

	writer.Header().Add("Content-Type", "application/json")
	json.NewEncoder(writer).Encode(tasks)
}

func (taskHandler *TaskHandler) CreateTask(writer http.ResponseWriter, request *http.Request) {
	var task models.Task

	err := json.NewDecoder(request.Body).Decode(&task)
	if err != nil {
		http.Error(writer, err.Error(), http.StatusBadRequest)
		return
	}

	// We need to make it return the ID by querying with RETURNING id in the end. Also, in order to set the ID, we can use the .Scan(&task.ID) which will return the pointer to the task.ID var
	err = taskHandler.db.QueryRow("INSERT INTO tasks (title, description, status) VALUES ($1, $2, $3) RETURNING id", task.Title, task.Description, task.Status).Scan(&task.ID)
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		return
	}

	writer.WriteHeader(http.StatusCreated)
	writer.Header().Add("Content-Type", "application/json")
	json.NewEncoder(writer).Encode(task)
}

func (taskHandler *TaskHandler) UpdateTask(writer http.ResponseWriter, request *http.Request) {
	var task models.Task

	err := json.NewDecoder(request.Body).Decode(&task)
	if err != nil {
		http.Error(writer, err.Error(), http.StatusBadRequest)
		return
	}

	vars := mux.Vars(request)
	id, err := strconv.Atoi(vars["id"])
	if err != nil {
		http.Error(writer, err.Error(), http.StatusBadRequest)
		return
	}

	task.ID = id
	result, err := taskHandler.db.Exec("UPDATE tasks SET title = $1, description = $2, status = $3 WHERE id = $4", task.Title, task.Description, task.Status, task.ID)
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		return
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		return
	}

	if rowsAffected == 0 {
		http.Error(writer, "No rows affected", http.StatusNotFound)
		return
	}

	writer.WriteHeader(http.StatusOK)
	writer.Header().Add("Content-Type", "application/json")
	json.NewEncoder(writer).Encode(task)
}

func (taskHandler *TaskHandler) DeleteTask(writer http.ResponseWriter, request *http.Request) {
	vars := mux.Vars(request)
	id, err := strconv.Atoi(vars["id"])
	if err != nil {
		http.Error(writer, err.Error(), http.StatusBadRequest)
		return
	}

	result, err := taskHandler.db.Exec("DELETE FROM tasks WHERE id = $1", id)
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		return
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		http.Error(writer, err.Error(), http.StatusInternalServerError)
		return
	}

	if rowsAffected == 0 {
		http.Error(writer, "No rows affected", http.StatusNotFound)
		return
	}
	writer.WriteHeader(http.StatusNoContent)
}
