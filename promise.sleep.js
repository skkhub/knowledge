function sleep(time) {
  return new Promise(res => setTimeout(res, time));
}

class Scleduler {
  k = 0
  max = 2
  queue = []
  run() {
    while (this.queue.length > 0) {
      const {promiseCreator, resolve, reject} = this.queue.shift();
      this.k++;
      promiseCreator().then(res => {
        resolve(res);
        this.k--;
        this.run();
      }, err => {
        reject(err);
        this.k--;
        this.run();
      })
    }
  }
  add(promiseCreator) {
    return new Promise((resolve, reject) => {
      this.queue.push({promiseCreator, resolve, reject});
      if (this.k < this.max) {
        this.run();
      }
    });
  }
}

const addTask = (num, time) => sleep(time).then(() => num);
const scleduler = new Scleduler();
scleduler.add(() => addTask(1000, 1000)).then(res => {
  console.log(res);
});
scleduler.add(() => addTask(100, 100)).then(res => {
  console.log(res);
});
scleduler.add(() => addTask(400, 400)).then(res => {
  console.log(res);
});
