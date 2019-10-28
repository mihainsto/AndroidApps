from flask import Flask, jsonify, request

app = Flask(__name__)

def addUserDB(username, password):
    with open('users.txt','a') as f:
        f.write(username)
        f.write('\n')
        f.write(password)
        f.write('\n')

def verifyUserDB(username, password):
    with open('users.txt','r') as f:
        lines = f.readlines()
        lines = [x.replace('\n','') for x in lines]
        for i in range(0, len(lines), 2):
            if lines[i] == username and lines[i+1] == password:
                return True
    return False
            

def addTask(username, task):
    task = task.replace('\01',' ')
    with open('tasks.txt','a') as f:
        f.write(username)
        f.write('\n')
        f.write(task)
        f.write('\n')

def getTasks(username):
    tasks = []
    with open('tasks.txt','r') as f:
        lines = f.readlines()
        lines = [x.replace('\n','') for x in lines]

        for i in range(0, len(lines), 2):
            if lines[i] == username:
                tasks.append(lines[i+1])
    return tasks
                

@app.route('/auth/<string:username>/<string:password>', methods=['GET'])
def verify_user(username, password):
    if(verifyUserDB(username, password)):
        return jsonify({'result':'True'})
    else:
        return jsonify({'result':'False'})

@app.route('/register/<string:username>/<string:password>', methods=['GET'])
def register_user(username, password):
    addUserDB(username, password)
    return jsonify({'stauts':'done'})


@app.route('/task/addTask/<string:username>/<string:task>', methods=['GET'])
def recive_task(username, task):
    addTask(username,task)
    return jsonify({'stauts':'done'})

@app.route('/task/getTasks/<string:username>',methods=['GET'])
def sent_task(username):
    tasks = getTasks(username)
    return jsonify({'tasks':tasks})
if __name__ =='__main__':
    app.run(debug=True)