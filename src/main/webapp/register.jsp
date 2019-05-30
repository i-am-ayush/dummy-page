<%--suppress ALL --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Registration</title>

    <style type="text/css">
        #seekerform {
            display: none
        }

        #sitterform {
            display: none
        }

        #radiocheck {
            display: none
        }

        #submit {
            display: none
        }

    </style>

</head>
<body>
<div class="container">
    <h3>Registration</h3>
    Seeker:
    <input type="radio" value="seeker" name="membertype" id="radioseeker_selected" onclick="viewform()">
    <span>     </span>
    Sitter:
    <input type="radio" value="sitter" name="membertype" id="radiositter_selected" onclick="viewform()">

    <form id="seekerform" action="registerseeker" method="post">
        <h4>Seeker Form</h4>
        <label for="radioseeker">Seeker:</label>
        <div class="form-group"><input type="radio" value="seeker" name="membertype" id="radioseeker"></div>
        <label for="firstname">First name:</label>
        <div class="form-group"><input class="form-control" type="text" name="firstname" id="firstname"></div>
        <label for="lastname">Last name:</label>
        <div class="form-group"><input class="form-control" type="text" name="lastname" id="lastname"></div>
        <label for="phone">Phone number:</label>
        <div class="form-group"><input class="form-control" type="text" name="phone" id="phone"></div>
        <label for="email">Email:</label>
        <div class="form-group"><input class="form-control" type="text" name="email" id="email"></div>
        <label for="address">Address:</label>
        <div class="form-group"><input class="form-control" type="text" name="address" id="address"></div>
        <label for="spousename">Spouse name:</label>
        <div class="form-group"><input class="form-control" type="text" name="spousename" id="spousename"></div>
        <label for="children">No of Children:</label>
        <div class="form-group"><input class="form-control" type="text" name="children" id="children"></div>
        <label for="password">Password:</label>
        <div class="form-group"><input class="form-control" type="password" name="password" id="password"></div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

    <form id="sitterform" action="registersitter" method="post">
        <h4>Sitter Form</h4>
        <label for="radiositter">Sitter:</label>
        <div class="form-group"><input type="radio" value="sitter" name="membertype" id="radiositter"></div>
        <label for="firstname">First name:</label>
        <div class="form-group"><input class="form-control" type="text" name="firstname" id="firstname"></div>
        <label for="lastname">Last name:</label>
        <div class="form-group"><input class="form-control" type="text" name="lastname" id="lastname"></div>
        <label for="phone">Phone number:</label>
        <div class="form-group"><input class="form-control" type="text" name="phone" id="phone"></div>
        <label for="email">Email:</label>
        <div class="form-group"><input class="form-control" type="text" name="email" id="email"></div>
        <label for="address">Address:</label>
        <div class="form-group"><input class="form-control" type="text" name="address" id="address"></div>
        <label for="experience">Experience:</label>
        <div class="form-group"><input class="form-control" type="text" name="experience" id="experience"></div>
        <label for="expectedpay">Expected Pay:</label>
        <div class="form-group"><input class="form-control" type="text" name="expectedpay" id="expectedpay"></div>
        <label for="password">Password:</label>
        <div class="form-group"><input class="form-control" type="password" name="password" id="password"></div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script type="text/javascript">

    function viewform() {

        var seeker_selected = document.getElementById("radioseeker_selected").checked;
        var sitter_selected = document.getElementById("radiositter_selected").checked;
        var seekerform = document.getElementById("seekerform");
        var sitterform = document.getElementById("sitterform");
        if (seeker_selected) {
            document.getElementById("radioseeker").checked = seeker_selected;
            seekerform.style.display = "inline";
            sitterform.style.display = "none";
        }
        if (sitter_selected) {
            document.getElementById("radiositter").checked = sitter_selected;
            sitterform.style.display = "inline";
            seekerform.style.display = "none";
        }
    }
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

</body>
</html>