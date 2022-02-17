<%@ page import="ru.job4j.todo.model.Item" %>
<%@ page import="ru.job4j.todo.store.HbnStore" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script>
        function validate() {
            var rsl = true;
            var valueTask = $('#newTask').val();
            if (valueTask.length === 0) {
                alert($('#newTask').attr('title'));
                rsl = false;
            }
            return rsl;
        }
    </script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Список задач</title>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Форма создания новой заявки
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/index.do" method="post">
                    <div class="form-group">
                        <label>Наименование новой задачи</label>
                        <input type="text" class="form-control" title="Введите имя новой задачи" id="newTask" name="description">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate();">Добавить задачу</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Невыполненные задачи:
            </div>
            <div class="card-body">
                <table class="table">
                    <tbody>
                    <% for (Item item : HbnStore.instOf().findByStatusTask(false)) { %>
                    <tr>
                        <td>
                            <%=item.getDescription()%>
                            <a href="<%=request.getContextPath()%>/change.do?id=<%=item.getId()%>">
                                <span class="glyphicon glyphicon-ok"></span>
                            </a>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <div class="btn-group">
                    <form action="<%=request.getContextPath()%>/index.do" method="get">
                        <button type="submit" class="btn btn-outline-primary btn-sm">Отобразить завершенные</button>
                    </form>
                    <form action="<%=request.getContextPath()%>/index.do" method="get">
                        <button type="submit" class="btn btn-outline-primary btn-sm">Отобразить все заявки</button>
                    </form>
                </div>
            </div>
            <div class="card-body">
                <table class="table">
                    <tbody>
                    <div class="card-header">
                        Завершенные задачи:
                    </div>
                    <c:forEach items="${taskDoneTrue}" var="taskDone">
                        <tr>
                            <td>
                                <c:out value="${taskDone.description}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-body">
                <table class="table">
                    <tbody>
                    <div class="card-header">
                        Задачи за весь период:
                    </div>
                    <c:forEach items="${allTasks}" var="task">
                        <tr>
                            <td>
                                <c:out value="${task.description}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
