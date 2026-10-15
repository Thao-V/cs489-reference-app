
import { useEffect, useState } from "react";

//It is just for an example. We need to secure the username and password.
const username = "admin";
const password = "admin123";

const credentials = btoa(`${username}:${password}`);

interface Book {
  id: number;
  title: string;
  ISBN: string;
  author: string;
}

const API = "/api/books";

export default function App() {
  const [books, setBooks] = useState<Book[]>([]);
  const [form, setForm] = useState({
    title: "",
    ISBN: "",
    author: "",
  });

  useEffect(() => {
    async function loadBooks() {
      const raw = await fetch(API, {
            method: "GET",
            headers: {
                "Authorization": `Basic ${credentials}`
            }
        })
      if (!raw.ok) {
        alert("Failed to load books");
        return;
      }
      const res = await raw.json();
      setBooks(res);
    }
    loadBooks();
  }, []);

  async function addBook(e: React.SubmitEvent<HTMLFormElement>) {
    e.preventDefault();

    const res = await fetch(API, {
      method: "POST",
      headers: { 
        "Content-Type": "application/json",
        "Authorization": `Basic ${credentials}`
      },
      body: JSON.stringify(form),
    });

    if (!res.ok) {
      alert("Failed to add book");
      return;
    }

    setForm({ title: "", ISBN: "", author: "" });
    const savedBook = await res.json();
    const newBooks = [...books, savedBook];
    setBooks(newBooks);
  }

  return (
    <div className="max-w-4xl mx-auto p-6">
      <h1 className="text-3xl font-bold mb-6">eLibrary</h1>

      <form onSubmit={addBook} className="space-y-3 mb-8">
        <h2 className="text-xl font-semibold">Add Book</h2>

        <input
          className="border p-2 rounded w-full"
          placeholder="Title"
          required
          value={form.title}
          onChange={e => setForm({ ...form, title: e.target.value })}
        />

        <input
          className="border p-2 rounded w-full"
          placeholder="ISBN"
          required
          value={form.ISBN}
          onChange={e => setForm({ ...form, ISBN: e.target.value })}
        />

        <input
          className="border p-2 rounded w-full"
          placeholder="Author"
          required
          value={form.author}
          onChange={e => setForm({ ...form, author: e.target.value })}
        />

        <button className="bg-blue-600 text-white px-4 py-2 rounded">
          Add Book
        </button>
      </form>

      <h2 className="text-xl font-semibold mb-3">Book List</h2>

      <table className="w-full border-collapse border">
        <thead className="bg-gray-100">
          <tr>
            <th className="border p-2">ID</th>
            <th className="border p-2">Title</th>
            <th className="border p-2">ISBN</th>
            <th className="border p-2">Author</th>
          </tr>
        </thead>
        <tbody>
          {books.map(book => (
            <tr key={book.id}>
              <td className="border p-2">{book.id}</td>
              <td className="border p-2">{book.title}</td>
              <td className="border p-2">{book.ISBN}</td>
              <td className="border p-2">{book.author}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
