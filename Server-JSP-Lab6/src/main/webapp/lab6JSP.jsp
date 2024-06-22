<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Hello World!!!
<form name="display" action="DisplayModelServlet">
<table>
	<thead>
		<tr>
			<th>OptionSet</th>
			<th>Option</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Color</td>
			<td>
				<select name="color">
					<option value=""></option>
					<option value=""></option>
					<option value=""></option>
				</select>
			</td>
		</tr>
			<tr>
			<td>Transmission</td>
			<td>
				<select name="transmission">
					<option value=""></option>
					<option value=""></option>
					<option value=""></option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Brakes/Traction Control</td>
			<td>
				<select name="Brakes/Traction Control">
					<option value=""></option>
					<option value=""></option>
					<option value=""></option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Side Impact Air Bags</td>
			<td>
				<select name="side impact air bags">
					<option value=""></option>
					<option value=""></option>
					<option value=""></option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Power Moonroof</td>
			<td>
				<select name="power moonroof">
					<option value=""></option>
					<option value=""></option>
					<option value=""></option>
				</select>
			</td>
		</tr>
	</tbody>
</table>
<input type="submit" value="Done">
</form>
</body>
</html>