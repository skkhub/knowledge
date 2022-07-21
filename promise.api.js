const p1 = Promise.reject(1);
const p2 = Promise.resolve(2);
const p3 = Promise.resolve(3);

const result = Promise.allSettled([p1, p2, p3]).then(res => {
  console.log(res);
  // [{status: 'rejected', reason: 1}, {status: 'fulfilled', value: 2}, {status: 'fulfilled', value: 3}]
});
 
const result = Promise.allSettled([p1, p2, p3]).then(res => {
  console.log(res); // 2
});
const result = Promise.race([p1, p2, p3]).then(res => {
  console.log(res); // 1 返回第一个完成状态对应的返回值，无论reject还是fulfilled
});
console.log(result);