import turtle
colors = ['red', 'purple', 'blue', 'green', 'orange', 'yellow']

t = turtle.Pen()
turtle.bgcolor('black')
turtle.speed(speed=2)
for x in range(360):
	t.pencolor(colors[x%6])
	t.width(x/400 + 1)
	t.forward(x)
	t.left(59)
