import React from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { AuthProvider } from './contexts/AuthContext'
import Header from './components/Header'
import SignIn from './components/auth/SignIn'
import SignUp from './components/auth/SignUp'
import AuthCallback from './components/auth/AuthCallback'
import ProtectedRoute from './components/auth/ProtectedRoute'

// Example components - replace with your actual components
const Home = () => (
  <div className="min-h-screen bg-gradient-light dark:bg-gradient-dark pt-20 px-4">
    <div className="max-w-4xl mx-auto text-center">
      <h1 className="text-4xl font-bold text-primary dark:text-secondary mb-4">
        Welcome to Fici
      </h1>
      <p className="text-lg text-primary/70 dark:text-secondary/70">
        Your authentication is now working! This is the home page.
      </p>
    </div>
  </div>
)

const Products = () => (
  <div className="min-h-screen bg-gradient-light dark:bg-gradient-dark pt-20 px-4">
    <div className="max-w-4xl mx-auto text-center">
      <h1 className="text-4xl font-bold text-primary dark:text-secondary mb-4">
        Products
      </h1>
      <p className="text-lg text-primary/70 dark:text-secondary/70">
        This is the products page.
      </p>
    </div>
  </div>
)

const About = () => (
  <div className="min-h-screen bg-gradient-light dark:bg-gradient-dark pt-20 px-4">
    <div className="max-w-4xl mx-auto text-center">
      <h1 className="text-4xl font-bold text-primary dark:text-secondary mb-4">
        About Us
      </h1>
      <p className="text-lg text-primary/70 dark:text-secondary/70">
        This is the about page.
      </p>
    </div>
  </div>
)

const Contact = () => (
  <div className="min-h-screen bg-gradient-light dark:bg-gradient-dark pt-20 px-4">
    <div className="max-w-4xl mx-auto text-center">
      <h1 className="text-4xl font-bold text-primary dark:text-secondary mb-4">
        Contact Us
      </h1>
      <p className="text-lg text-primary/70 dark:text-secondary/70">
        This is the contact page.
      </p>
    </div>
  </div>
)

const CartPage = () => (
  <ProtectedRoute>
    <div className="min-h-screen bg-gradient-light dark:bg-gradient-dark pt-20 px-4">
      <div className="max-w-4xl mx-auto text-center">
        <h1 className="text-4xl font-bold text-primary dark:text-secondary mb-4">
          Shopping Cart
        </h1>
        <p className="text-lg text-primary/70 dark:text-secondary/70">
          This is a protected cart page. You must be logged in to view this.
        </p>
      </div>
    </div>
  </ProtectedRoute>
)

const Profile = () => (
  <ProtectedRoute>
    <div className="min-h-screen bg-gradient-light dark:bg-gradient-dark pt-20 px-4">
      <div className="max-w-4xl mx-auto text-center">
        <h1 className="text-4xl font-bold text-primary dark:text-secondary mb-4">
          Profile Settings
        </h1>
        <p className="text-lg text-primary/70 dark:text-secondary/70">
          This is a protected profile page. You must be logged in to view this.
        </p>
      </div>
    </div>
  </ProtectedRoute>
)

function App() {
  return (
    <Router>
      <AuthProvider>
        <div className="App">
          <Header />
          <Routes>
            {/* Public Routes */}
            <Route path="/" element={<Home />} />
            <Route path="/products" element={<Products />} />
            <Route path="/about" element={<About />} />
            <Route path="/contact" element={<Contact />} />
            
            {/* Authentication Routes */}
            <Route path="/auth/signin" element={<SignIn />} />
            <Route path="/auth/signup" element={<SignUp />} />
            <Route path="/auth/callback" element={<AuthCallback />} />
            
            {/* Protected Routes */}
            <Route path="/cartpage" element={<CartPage />} />
            <Route path="/profile" element={<Profile />} />
            
            {/* Catch all route */}
            <Route path="*" element={<Home />} />
          </Routes>
        </div>
      </AuthProvider>
    </Router>
  )
}

export default App