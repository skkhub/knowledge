## worker使用注意

- 在worker内不能直接操作dom节点（防止主线程与worker同时操作一个dom的情况）

- 在worker内不能使用某些window对象的方法和属性，例如可以使用xhr

- workers和主线程间数据传递通过postMessage，onmessage处理响应（Message中的data属性），数据被复制过来，不是引用关系

## worker语法
```js
// 在main.js
var myWorker = new Worker('worker.js');
first.onchange = function() {
  myWorker.postMessage([first.value,second.value]);
  console.log('Message posted to worker');
}

second.onchange = function() {
  myWorker.postMessage([first.value,second.value]);
  console.log('Message posted to worker');
}

// 在worker.js
onmessage = function(e) {
  console.log('Message received from main script');
  var workerResult = 'Result: ' + (e.data[0] * e.data[1]);
  console.log('Posting message back to main script');
  postMessage(workerResult);
}
```
- 主线程使用onmessage和postMessage() 必须挂在 worker 对象上，而在 worker 中使用时不用这样做。原因是，在 worker 内部，worker 是有效的全局作用域。

- 终止worker
```js
// main.js
myWorker.terminate();

// worker.js
close(); // 直接执行，无需挂在某个对象下
```