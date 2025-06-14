import { useEffect, useState } from 'react';
import './App.css';

interface CarBoard {
  id: number;
  title: string;
  content: string;
  author: string;
  imageUrl?: string;
  createdAt: string;
}

function App() {
  const [posts, setPosts] = useState<CarBoard[]>([]);
  const [form, setForm] = useState({ title: '', content: '', author: '', imageUrl: '' });
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetch('http://localhost:8080/api/board')
      .then(res => res.json())
      .then(setPosts);
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    const res = await fetch('http://localhost:8080/api/board', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form),
    });
    const newPost = await res.json();
    setPosts([newPost, ...posts]);
    setForm({ title: '', content: '', author: '', imageUrl: '' });
    setLoading(false);
  };

  return (
    <div className="container">
      <h1>🚗 내 자동차 자랑 게시판</h1>
      <form className="board-form" onSubmit={handleSubmit}>
        <input name="title" value={form.title} onChange={handleChange} placeholder="제목" required />
        <input name="author" value={form.author} onChange={handleChange} placeholder="작성자" required />
        <input name="imageUrl" value={form.imageUrl} onChange={handleChange} placeholder="이미지 URL (선택)" />
        <textarea name="content" value={form.content} onChange={handleChange} placeholder="내용" required />
        <button type="submit" disabled={loading}>{loading ? '등록 중...' : '게시글 등록'}</button>
      </form>
      <div className="board-list">
        {posts.length === 0 && <p>게시글이 없습니다.</p>}
        {posts.map(post => (
          <div className="board-item" key={post.id}>
            <h2>{post.title}</h2>
            <div className="meta">by {post.author} | {new Date(post.createdAt).toLocaleString()}</div>
            {post.imageUrl && <img src={post.imageUrl} alt="car" className="car-image" />}
            <p>{post.content}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
