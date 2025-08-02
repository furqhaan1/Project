import { Moon, Search, ShoppingCart, Sun, UserRound, Menu, X, LogOut, Settings } from 'lucide-react'
import React, { useEffect, useState } from 'react'
import { Link, NavLink } from 'react-router-dom'
import { useAuth } from '../contexts/AuthContext'
import logo from '../assets/Fici Logo.png'
import logoLight from '../assets/Fici Logo Light.png'

const Header: React.FC = () => {
  const [mode, setMode] = useState<'light' | 'dark'>('light');
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const [showUserMenu, setShowUserMenu] = useState(false);
  
  const { user, signOut, loading } = useAuth();

  const toggleMode = () => {
    setMode((prevMode) => {
      const newMode = prevMode === 'light' ? 'dark' : 'light';
      document.documentElement.classList.toggle('dark', newMode === 'dark');
      localStorage.setItem('mode', newMode);
      return newMode;
    });
  };

  const toggleMobileMenu = () => {
    setIsMobileMenuOpen(!isMobileMenuOpen);
  };

  const closeMobileMenu = () => {
    setIsMobileMenuOpen(false);
  };

  const toggleUserMenu = () => {
    setShowUserMenu(!showUserMenu);
  };

  const closeUserMenu = () => {
    setShowUserMenu(false);
  };

  const handleSignOut = async () => {
    await signOut();
    closeUserMenu();
  };

  // Get user display name
  const getUserDisplayName = () => {
    if (!user) return null;
    
    return user.user_metadata?.full_name || 
           user.user_metadata?.name || 
           user.email?.split('@')[0] || 
           'User';
  };

  // Get user avatar
  const getUserAvatar = () => {
    return user?.user_metadata?.avatar_url || null;
  };

  useEffect(() => {
    const storedMode = localStorage.getItem('mode');
    if (storedMode === 'dark' || storedMode === 'light') {
      setMode(storedMode);
      document.documentElement.classList.toggle('dark', storedMode === 'dark');
    }
  }, []);

  // Close user menu when clicking outside
  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (showUserMenu && !(event.target as Element).closest('.user-menu-container')) {
        closeUserMenu();
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [showUserMenu]);

  return (
    <header className='bg-gradient-light dark:bg-gradient-dark w-full sticky top-0 left-0 right-0 z-50'>
      <div className='py-4 mx-auto flex items-center justify-between px-4 md:px-16'>
        {/* Mobile Menu Button - Left side */}
        <div className='md:hidden flex items-center gap-2'>
          <button onClick={toggleMobileMenu} className='cursor-pointer'>
            {isMobileMenuOpen ? (
              <X className='text-primary dark:text-secondary w-6 h-6 hover:text-accent' />
            ) : (
              <Menu className='text-primary dark:text-secondary w-6 h-6 hover:text-accent' />
            )}
          </button>
        </div>

        {/* Logo - Centered on mobile, left on desktop */}
        <div className='flex-1 md:flex-none flex justify-center md:justify-start'>
          <NavLink to='/' className='cursor-pointer hover:opacity-80 transition-opacity'>
            <img src={mode === 'light' ? logo : logoLight} alt="logo" className='w-16 h-16' />
          </NavLink>
        </div>

        {/* Desktop Navigation */}
        <nav className='hidden md:flex items-center gap-4 font-primary'>
          <NavLink className={({ isActive }) => isActive ? 'text-primary-active dark:text-secondary-active hover:text-accent' : 'text-primary dark:text-secondary hover:text-accent'} to='/'>Home</NavLink>
          <NavLink className={({ isActive }) => isActive ? 'text-primary-active dark:text-secondary-active hover:text-accent' : 'text-primary dark:text-secondary hover:text-accent'} to='/products'>Products</NavLink>
          <NavLink className={({ isActive }) => isActive ? 'text-primary-active dark:text-secondary-active hover:text-accent' : 'text-primary dark:text-secondary hover:text-accent'} to='/about'>About</NavLink>
          <NavLink to='/contact' className={({ isActive }) => isActive ? 'text-primary-active dark:text-secondary-active hover:text-accent' : 'text-primary dark:text-secondary hover:text-accent'}>Contact</NavLink>
        </nav>

        {/* Desktop Actions */}
        <div className='hidden md:flex items-center gap-4'>
          <button className='cursor-pointer'><Search className='text-primary dark:text-secondary w-5 h-5 hover:text-accent' /></button>
          <button className='cursor-pointer'>
            <NavLink to='/cartpage' className={({ isActive }) => isActive ? 'text-primary-active dark:text-secondary-active hover:text-accent' : 'text-primary dark:text-secondary hover:text-accent'}>
              <ShoppingCart className='text-primary dark:text-secondary w-5 h-5 hover:text-accent' />
            </NavLink>
          </button>
          
          {/* User Authentication Section */}
          {loading ? (
            <div className='w-5 h-5 bg-gray-300 dark:bg-gray-600 rounded animate-pulse'></div>
          ) : user ? (
            <div className="relative user-menu-container">
              <button 
                onClick={toggleUserMenu}
                className='flex items-center gap-2 cursor-pointer hover:opacity-80 transition-opacity'
              >
                {getUserAvatar() ? (
                  <img 
                    src={getUserAvatar()!} 
                    alt={getUserDisplayName()!}
                    className='w-8 h-8 rounded-full object-cover'
                  />
                ) : (
                  <UserRound className='text-primary dark:text-secondary w-5 h-5' />
                )}
                <span className='text-sm text-primary dark:text-secondary font-medium'>
                  {getUserDisplayName()}
                </span>
              </button>

              {/* User Dropdown Menu */}
              {showUserMenu && (
                <div className="absolute right-0 mt-2 w-48 bg-white dark:bg-[color:var(--color-dark2)] rounded-lg shadow-xl border border-gray-200 dark:border-gray-700 py-2 z-50">
                  <div className="px-4 py-2 border-b border-gray-200 dark:border-gray-700">
                    <p className="text-sm font-medium text-gray-900 dark:text-gray-100">
                      {getUserDisplayName()}
                    </p>
                    <p className="text-xs text-gray-500 dark:text-gray-400 truncate">
                      {user.email}
                    </p>
                  </div>
                  
                  <Link
                    to="/profile"
                    onClick={closeUserMenu}
                    className="flex items-center gap-2 px-4 py-2 text-sm text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
                  >
                    <Settings className="w-4 h-4" />
                    Profile Settings
                  </Link>
                  
                  <button
                    onClick={handleSignOut}
                    className="w-full flex items-center gap-2 px-4 py-2 text-sm text-red-600 dark:text-red-400 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
                  >
                    <LogOut className="w-4 h-4" />
                    Sign Out
                  </button>
                </div>
              )}
            </div>
          ) : (
            <Link 
              to="/auth/signin"
              className='flex items-center gap-2 cursor-pointer hover:opacity-80 transition-opacity'
            >
              <UserRound className='text-primary dark:text-secondary w-5 h-5' />
              <span className='text-sm text-primary dark:text-secondary font-medium'>
                Sign In
              </span>
            </Link>
          )}

          <div className='rounded-full bg-primary dark:bg-secondary p-2 flex items-center gap-2'>
            {mode === 'light' ? (
              <button onClick={toggleMode} className='cursor-pointer'><Sun className='text-white dark:text-primary w-5 h-5' /></button>
            ) : (
              <button onClick={toggleMode} className='cursor-pointer'><Moon className='text-white dark:text-primary w-5 h-5' /></button>
            )}
          </div>
        </div>

        {/* Mobile Actions - Right side */}
        <div className='md:hidden flex items-center gap-2'>
          <button className='cursor-pointer'><Search className='text-primary dark:text-secondary w-5 h-5 hover:text-accent' /></button>
          <button className='cursor-pointer'>
            <NavLink to='/cartpage' className={({ isActive }) => isActive ? 'text-primary-active dark:text-secondary-active hover:text-accent' : 'text-primary dark:text-secondary hover:text-accent'}>
              <ShoppingCart className='text-primary dark:text-secondary w-5 h-5 hover:text-accent' />
            </NavLink>
          </button>
          
          {/* Mobile User Authentication */}
          {loading ? (
            <div className='w-5 h-5 bg-gray-300 dark:bg-gray-600 rounded animate-pulse'></div>
          ) : user ? (
            <div className="relative user-menu-container">
              <button 
                onClick={toggleUserMenu}
                className='cursor-pointer hover:opacity-80 transition-opacity'
              >
                {getUserAvatar() ? (
                  <img 
                    src={getUserAvatar()!} 
                    alt={getUserDisplayName()!}
                    className='w-6 h-6 rounded-full object-cover'
                  />
                ) : (
                  <UserRound className='text-primary dark:text-secondary w-5 h-5' />
                )}
              </button>

              {/* Mobile User Dropdown Menu */}
              {showUserMenu && (
                <div className="absolute right-0 mt-2 w-48 bg-white dark:bg-[color:var(--color-dark2)] rounded-lg shadow-xl border border-gray-200 dark:border-gray-700 py-2 z-50">
                  <div className="px-4 py-2 border-b border-gray-200 dark:border-gray-700">
                    <p className="text-sm font-medium text-gray-900 dark:text-gray-100">
                      {getUserDisplayName()}
                    </p>
                    <p className="text-xs text-gray-500 dark:text-gray-400 truncate">
                      {user.email}
                    </p>
                  </div>
                  
                  <Link
                    to="/profile"
                    onClick={closeUserMenu}
                    className="flex items-center gap-2 px-4 py-2 text-sm text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
                  >
                    <Settings className="w-4 h-4" />
                    Profile Settings
                  </Link>
                  
                  <button
                    onClick={handleSignOut}
                    className="w-full flex items-center gap-2 px-4 py-2 text-sm text-red-600 dark:text-red-400 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
                  >
                    <LogOut className="w-4 h-4" />
                    Sign Out
                  </button>
                </div>
              )}
            </div>
          ) : (
            <Link to="/auth/signin" className='cursor-pointer'>
              <UserRound className='text-primary dark:text-secondary w-5 h-5 hover:text-accent' />
            </Link>
          )}

          <div className='rounded-full bg-primary dark:bg-secondary p-2'>
            {mode === 'light' ? (
              <button onClick={toggleMode} className='cursor-pointer'><Sun className='text-white dark:text-primary w-5 h-5' /></button>
            ) : (
              <button onClick={toggleMode} className='cursor-pointer'><Moon className='text-white dark:text-primary w-5 h-5' /></button>
            )}
          </div>
        </div>
      </div>

      {/* Mobile Menu Overlay */}
      {isMobileMenuOpen && (
        <div className='md:hidden fixed inset-0 bg-black bg-opacity-50 z-40' onClick={closeMobileMenu}>
          <div className='absolute top-0 left-0 w-64 h-full bg-[color:var(--color-light1)] dark:bg-[color:var(--color-dark2)] shadow-xl p-6' onClick={(e) => e.stopPropagation()}>
            <div className='flex justify-between items-center mb-8'>
              <h3 className='text-xl font-bold text-[color:var(--color-text-light)] dark:text-[color:var(--color-text-dark)]'>Menu</h3>
              <button onClick={closeMobileMenu} className='cursor-pointer'>
                <X className='text-primary dark:text-secondary w-6 h-6 hover:text-accent' />
              </button>
            </div>
            
            {/* Mobile Navigation */}
            <nav className='flex flex-col gap-4 mb-6'>
              <NavLink 
                to='/' 
                className={({ isActive }) => 
                  `text-lg font-medium transition-colors ${
                    isActive 
                      ? 'text-primary-active dark:text-secondary-active' 
                      : 'text-primary dark:text-secondary hover:text-accent'
                  }`
                }
                onClick={closeMobileMenu}
              >
                Home
              </NavLink>
              <NavLink 
                to='/products' 
                className={({ isActive }) => 
                  `text-lg font-medium transition-colors ${
                    isActive 
                      ? 'text-primary-active dark:text-secondary-active' 
                      : 'text-primary dark:text-secondary hover:text-accent'
                  }`
                }
                onClick={closeMobileMenu}
              >
                Products
              </NavLink>
              <NavLink 
                to='/about' 
                className={({ isActive }) => 
                  `text-lg font-medium transition-colors ${
                    isActive 
                      ? 'text-primary-active dark:text-secondary-active' 
                      : 'text-primary dark:text-secondary hover:text-accent'
                  }`
                }
                onClick={closeMobileMenu}
              >
                About
              </NavLink>
              <NavLink 
                to='/contact' 
                className={({ isActive }) => 
                  `text-lg font-medium transition-colors ${
                    isActive 
                      ? 'text-primary-active dark:text-secondary-active' 
                      : 'text-primary dark:text-secondary hover:text-accent'
                  }`
                }
                onClick={closeMobileMenu}
              >
                Contact
              </NavLink>
            </nav>

            {/* Mobile Authentication Section */}
            <div className="border-t border-gray-200 dark:border-gray-700 pt-4">
              {user ? (
                <div className="space-y-3">
                  <div className="flex items-center gap-3 pb-3 border-b border-gray-200 dark:border-gray-700">
                    {getUserAvatar() ? (
                      <img 
                        src={getUserAvatar()!} 
                        alt={getUserDisplayName()!}
                        className='w-10 h-10 rounded-full object-cover'
                      />
                    ) : (
                      <div className="w-10 h-10 bg-gray-300 dark:bg-gray-600 rounded-full flex items-center justify-center">
                        <UserRound className="w-6 h-6 text-gray-600 dark:text-gray-300" />
                      </div>
                    )}
                    <div>
                      <p className="font-medium text-gray-900 dark:text-gray-100">
                        {getUserDisplayName()}
                      </p>
                      <p className="text-xs text-gray-500 dark:text-gray-400 truncate">
                        {user.email}
                      </p>
                    </div>
                  </div>
                  
                  <Link
                    to="/profile"
                    onClick={closeMobileMenu}
                    className="flex items-center gap-3 text-gray-700 dark:text-gray-300 hover:text-accent transition-colors"
                  >
                    <Settings className="w-5 h-5" />
                    Profile Settings
                  </Link>
                  
                  <button
                    onClick={() => {
                      handleSignOut();
                      closeMobileMenu();
                    }}
                    className="flex items-center gap-3 text-red-600 dark:text-red-400 hover:text-red-500 transition-colors"
                  >
                    <LogOut className="w-5 h-5" />
                    Sign Out
                  </button>
                </div>
              ) : (
                <div className="space-y-3">
                  <Link
                    to="/auth/signin"
                    onClick={closeMobileMenu}
                    className="flex items-center gap-3 text-gray-700 dark:text-gray-300 hover:text-accent transition-colors"
                  >
                    <UserRound className="w-5 h-5" />
                    Sign In
                  </Link>
                  <Link
                    to="/auth/signup"
                    onClick={closeMobileMenu}
                    className="flex items-center gap-3 text-accent hover:text-accent/80 transition-colors"
                  >
                    <UserRound className="w-5 h-5" />
                    Create Account
                  </Link>
                </div>
              )}
            </div>
          </div>
        </div>
      )}
    </header>
  )
}

export default Header