## type 和 interface 的异同
### 相同点
- 都可以描述对象或函数
- 都可以扩展

### 不同点
1. 声明范围
> type 右边可以是任何类型，包括基本类型、元祖、类型表达式（&、|等）
> interface 右边必须为变量结构

2. 扩展形式
> type & 针对同一key的类型不同时，不会编译报错，会尽可能组合
> interface extends 针对同一key的类型不同时，会编译报错
```js
type a = {age: number};
type b = {name: string};
type c = a & b;


interface Name {
  name: string;
}
interface User extends Name {
  age: number;
}
interface User2 extends b {
  age: number;
}
type d = Name & {age: number};
```
3. 重复定义的表现形式
> type 会报错
> interface 会自动合并
```js
interface User {
  name: string;
  age: number;
}

interface User {
  hobbies: string[];
}
// {
//   name: string
//   age: number
//   hobbies: string[]
// }
```

4. type可以定义基本类型别名，但interface无法定义；同时，可以用typeof获取其类型
```js
type userName = string;
type stuNo = number;
type B = typeof 1
```

5. type可以声明联合类型
```js
// 联合类型
type Student = {stuNo: number} | {userName: string}
// 元组：定义数组每一项的具体类型。示例为[18,'elwin']
type Data = [number, string];
```