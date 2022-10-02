## 鲜知识
```JS
setTimeout(function() {}, 5000, 1,2,3); // 从第3个参数开始，都会当作回调方法的参数

// Promise chain
function compose(...fns) {
    return (x) => fns.reduce((p, fn) => p.then(fn), Promise.resolve(x));
}

function fn() {
    try {
        return 1;
    } catch(err) {
        return 2;
    } finally {
        return 3; // 始终返回3
    }
}

window.onerror = function(message, url, line) {
    console.log(message);
    return false; // 阻止浏览器默认报告错误的行为。相当于整个文档的try/catch语句
}
```

