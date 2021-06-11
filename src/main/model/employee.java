package main.model;

import javafx.beans.property.SimpleStringProperty;

public class employee {

    private SimpleStringProperty id;
    private SimpleStringProperty age;
    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty question;
    private SimpleStringProperty answer;
    private SimpleStringProperty role;

    public employee(String id, String name, String surname, String age, String username, String password, String question, String answer, String role) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.age = new SimpleStringProperty(age);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.question = new SimpleStringProperty(question);
        this.answer = new SimpleStringProperty(answer);
        this.role = new SimpleStringProperty(role);
    }

    public employee(String id, String name, String surname, String age, String username, String password) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.age = new SimpleStringProperty(age);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }


    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getAge() {
        return age.get();
    }

    public SimpleStringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getQuestion() {
        return question.get();
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public String getAnswer() {
        return answer.get();
    }

    public SimpleStringProperty answerProperty() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }

    public String getRole() {
        return role.get();
    }

    public SimpleStringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }
}
