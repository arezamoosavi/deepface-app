import requests


url = "http://localhost:8000/analyze"
my_img = {"file": open("image.jpeg", "rb")}
r = requests.post(url, files=my_img)

# convert server response into JSON format.
print(r.text)
