import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { supabase } from '../../lib/supabase'
import { Loader2, AlertCircle, CheckCircle } from 'lucide-react'

const AuthCallback: React.FC = () => {
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const navigate = useNavigate()

  useEffect(() => {
    const handleAuthCallback = async () => {
      try {
        // Get the URL hash parameters
        const hashParams = new URLSearchParams(window.location.hash.substring(1))
        const accessToken = hashParams.get('access_token')
        const refreshToken = hashParams.get('refresh_token')

        if (accessToken) {
          // Set the session from the tokens
          const { data, error } = await supabase.auth.setSession({
            access_token: accessToken,
            refresh_token: refreshToken || ''
          })

          if (error) {
            console.error('Error setting session:', error)
            setError('Authentication failed. Please try again.')
          } else if (data.user) {
            console.log('User authenticated successfully:', data.user)
            // Redirect to home page or dashboard
            navigate('/', { replace: true })
          }
        } else {
          // Check for error in URL
          const errorDescription = hashParams.get('error_description') || 'Authentication failed'
          setError(errorDescription)
        }
      } catch (err) {
        console.error('Auth callback error:', err)
        setError('An unexpected error occurred. Please try again.')
      } finally {
        setLoading(false)
      }
    }

    // Handle the callback
    handleAuthCallback()
  }, [navigate])

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gradient-light dark:bg-gradient-dark">
        <div className="text-center">
          <Loader2 className="w-8 h-8 animate-spin mx-auto text-accent mb-4" />
          <h2 className="text-xl font-semibold text-primary dark:text-secondary mb-2">
            Completing authentication...
          </h2>
          <p className="text-sm text-primary/70 dark:text-secondary/70">
            Please wait while we sign you in
          </p>
        </div>
      </div>
    )
  }

  if (error) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gradient-light dark:bg-gradient-dark px-4">
        <div className="max-w-md w-full">
          <div className="bg-white dark:bg-[color:var(--color-dark2)] rounded-xl shadow-xl p-8 text-center">
            <AlertCircle className="w-12 h-12 text-red-500 mx-auto mb-4" />
            <h2 className="text-xl font-semibold text-primary dark:text-secondary mb-2">
              Authentication Failed
            </h2>
            <p className="text-sm text-red-600 dark:text-red-400 mb-6">
              {error}
            </p>
            <button
              onClick={() => navigate('/auth/signin')}
              className="w-full px-4 py-2 bg-accent text-white rounded-lg hover:bg-accent/90 transition-colors"
            >
              Try Again
            </button>
          </div>
        </div>
      </div>
    )
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-light dark:bg-gradient-dark">
      <div className="text-center">
        <CheckCircle className="w-8 h-8 text-green-500 mx-auto mb-4" />
        <h2 className="text-xl font-semibold text-primary dark:text-secondary mb-2">
          Authentication Successful
        </h2>
        <p className="text-sm text-primary/70 dark:text-secondary/70">
          Redirecting you now...
        </p>
      </div>
    </div>
  )
}

export default AuthCallback