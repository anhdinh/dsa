import { useState, useEffect } from 'react'
import HeapTree from './HeapTree.jsx'
import './app.css'

export default function App() {
  const [data, setData] = useState([])

  useEffect(() => { fetchHeap() }, [])

  function fetchHeap() {
    fetch('/api/heap')
      .then(r => r.json())
      .then(arr => setData(Array.isArray(arr) ? arr : []))
      .catch(() => {})
  }

  function insert(value) {
    const v = parseInt(value)
    if (isNaN(v)) return
    fetch('/api/heap/insert?value=' + v, { method: 'POST' })
      .then(r => r.json())
      .then(arr => setData(Array.isArray(arr) ? arr : []))
      .catch(() => {})
  }

  function reset() {
    fetch('/api/heap/reset', { method: 'POST' })
      .then(r => r.json())
      .then(arr => setData(Array.isArray(arr) ? arr : []))
      .catch(() => {})
  }

  function addRandom() {
    const v = Math.floor(Math.random() * 50) + 1
    fetch('/api/heap/insert?value=' + v, { method: 'POST' })
      .then(r => r.json())
      .then(arr => setData(Array.isArray(arr) ? arr : []))
      .catch(() => {})
  }

  async function addBulk() {
    for (let v = 1; v <= 9; v++) {
      try {
        const r = await fetch('/api/heap/insert?value=' + v, { method: 'POST' })
        const arr = await r.json()
        setData(Array.isArray(arr) ? arr : [])
      } catch (e) {}
    }
  }

  const items = Array.isArray(data) ? data : []

  return (
    <div className="container">
      <h1>Min-Heap Visualizer</h1>

      <div className="controls">
        <input
          id="valueInput"
          onKeyDown={e => { if (e.key === 'Enter') insert(e.target.value); e.target.value = '' }}
          placeholder="Nhập số..."
          type="number"
        />
        <button className="btn-add" onClick={() => { const inp = document.getElementById('valueInput'); insert(inp.value); inp.value = '' }}>Thêm</button>
        <button className="btn-random" onClick={addRandom}>Random</button>
        <button className="btn-bulk" onClick={addBulk}>Thêm 1-9</button>
        <button className="btn-reset" onClick={reset}>Reset</button>
      </div>

      <div className="canvas">
        <HeapTree data={items} />
      </div>

      <div className="array-display">
        <h3>Array</h3>
        <div className="array-items">
          {items.map((v, i) => (
            <span key={i} className={'array-item' + (i === 0 ? ' root' : '')}>
              {v}
            </span>
          ))}
        </div>
      </div>

      <div className="note">Min-heap: mỗi node &le; các node con</div>
    </div>
  )
}
