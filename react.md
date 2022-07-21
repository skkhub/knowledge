## Hooks
- useState
> 变成响应式
```js
const Home = () => {
  const [a, setA] = useState(100);
  function plus() {
    // setA(a + 100);
    setA(function(prev) {
      return prev + 100;
    })
  }

  return <div>
    {a}
    <button onClick={plus}>plus</button>
  </div>
}
```
- useEffect
> 依赖变动而跟随执行
```js
useEffect(() => {
  // js
}, [data]);
```