const NODE_R = 22
const LEVEL_H = 70
const LEAF_SPACING = 70

function calcPositions(arr) {
  if (!arr || arr.length === 0) return []
  const n = arr.length
  const h = Math.ceil(Math.log2(n + 1))
  const x = new Array(n)

  // Assign each node a position based on its index within its level
  for (let i = 0; i < n; i++) {
    const lv = Math.floor(Math.log2(i + 1))
    const levelStart = (1 << lv) - 1
    const idxInLevel = i - levelStart
    const levelUnit = LEAF_SPACING * (1 << (h - 1 - lv))
    x[i] = idxInLevel * levelUnit
  }

  // Center parents between children (when children exist)
  for (let i = n - 1; i >= 0; i--) {
    const l = 2 * i + 1, r = 2 * i + 2
    if (r < n) x[i] = (x[l] + x[r]) / 2
    else if (l < n) x[i] = x[l]
  }
  return x
}

export default function HeapTree({ data }) {
  if (!Array.isArray(data) || data.length === 0)
    return <div className="empty">(heap rỗng)</div>

  const n = data.length
  const h = Math.ceil(Math.log2(n + 1))
  const x = calcPositions(data)
  const leafCnt = 1 << (h - 1)
  const svgW = Math.max((leafCnt - 1) * LEAF_SPACING + 80, 200)
  const svgH = (h - 1) * LEVEL_H + 60

  const edges = []
  const nodes = []

  for (let i = 0; i < n; i++) {
    const l = 2 * i + 1, r = 2 * i + 2
    const lv = Math.floor(Math.log2(i + 1))
    const py = lv * LEVEL_H + NODE_R
    const px = x[i] + NODE_R

    if (l < n) {
      const cy = Math.floor(Math.log2(l + 1)) * LEVEL_H + NODE_R
      edges.push(
        <line key={'e' + i + 'l'} x1={px} y1={py} x2={x[l] + NODE_R} y2={cy}
          stroke="#89b4fa" strokeWidth="2" />
      )
    }
    if (r < n) {
      const cy = Math.floor(Math.log2(r + 1)) * LEVEL_H + NODE_R
      edges.push(
        <line key={'e' + i + 'r'} x1={px} y1={py} x2={x[r] + NODE_R} y2={cy}
          stroke="#89b4fa" strokeWidth="2" />
      )
    }

    const cx = x[i] + NODE_R
    const cy = lv * LEVEL_H + NODE_R
    nodes.push(
      <g key={'n' + i}>
        <circle cx={cx} cy={cy} r={NODE_R - 2} fill="#89b4fa" stroke="#74c7ec" strokeWidth="2" filter="url(#shadow)" />
        <text x={cx} y={cy + 1} textAnchor="middle" dominantBaseline="central"
          fill="#1e1e2e" fontWeight="bold" fontSize="15" fontFamily="'Segoe UI', Arial, sans-serif">
          {data[i]}
        </text>
      </g>
    )
  }

  return (
    <svg width={svgW} height={svgH}>
      <defs>
        <filter id="shadow" x="-20%" y="-20%" width="140%" height="140%">
          <feDropShadow dx="0" dy="2" stdDeviation="3" floodOpacity="0.3" />
        </filter>
      </defs>
      {edges}
      {nodes}
    </svg>
  )
}
