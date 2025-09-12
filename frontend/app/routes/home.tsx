import type { Route } from "./+types/home";

export function meta({}: Route.MetaArgs) {
  return [
    { title: "Welcome to Code Battle" },
    { name: "description", content: "Welcome to our coding battle platform!" },
  ];
}

export default function Home() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50">
      <di
        <h1 className="text-6xl font-bold text-gray-900 mb-4">
          Welcome to Code Battle
        </h1>
        <p className="text-xl text-gray-600">
          Ready to start coding? Let's begin your journey!
        </p>
      </div>
    </div>
  );
}
