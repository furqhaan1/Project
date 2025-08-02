# Supabase OAuth Setup and Testing Guide

## 🚀 Quick Start

Follow these steps to set up Supabase authentication with email and Google OAuth in your local development environment.

## 📋 Prerequisites

- Node.js 18+ installed
- A Supabase account ([supabase.com](https://supabase.com))
- A Google Cloud Console account (for Google OAuth)

## 🛠️ Step 1: Install Dependencies

```bash
npm install
```

The required Supabase dependency (`@supabase/supabase-js`) is already included in your `package.json`.

## 🔧 Step 2: Create Supabase Project

1. Go to [supabase.com](https://supabase.com) and sign in
2. Click "New Project"
3. Choose your organization and give your project a name
4. Set a strong database password
5. Select a region close to your users
6. Click "Create new project"

Wait for the project to be set up (usually takes 1-2 minutes).

## 🔑 Step 3: Get Supabase Credentials

1. In your Supabase dashboard, go to **Settings** → **API**
2. Copy the following values:
   - **Project URL** (something like `https://your-project.supabase.co`)
   - **anon public key** (starts with `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9`)

## 📝 Step 4: Environment Variables

1. Copy `.env.example` to `.env.local`:
```bash
cp .env.example .env.local
```

2. Update `.env.local` with your Supabase credentials:
```env
VITE_SUPABASE_URL=https://your-project.supabase.co
VITE_SUPABASE_ANON_KEY=your_supabase_anon_key_here
VITE_SITE_URL=http://localhost:5173
```

## 🔐 Step 5: Configure Authentication in Supabase

### Enable Email Authentication

1. Go to **Authentication** → **Settings** in your Supabase dashboard
2. Under **Auth Settings**, ensure:
   - **Enable email confirmations** is ON (for production)
   - **Enable email confirmations** can be OFF for testing

### Set up Google OAuth

1. Go to **Authentication** → **Providers** in Supabase
2. Find **Google** and click the toggle to enable it
3. You'll need to configure Google OAuth credentials:

#### Google Cloud Console Setup:

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select an existing one
3. Enable the **Google+ API**:
   - Go to **APIs & Services** → **Library**
   - Search for "Google+ API" and enable it
4. Create OAuth 2.0 credentials:
   - Go to **APIs & Services** → **Credentials**
   - Click **Create Credentials** → **OAuth 2.0 Client IDs**
   - Choose **Web application**
   - Add these to **Authorized redirect URIs**:
     ```
     https://your-project.supabase.co/auth/v1/callback
     http://localhost:5173/auth/callback
     ```
   - Add these to **Authorized JavaScript origins**:
     ```
     https://your-project.supabase.co
     http://localhost:5173
     ```

5. Copy the **Client ID** and **Client Secret**

#### Back in Supabase:

1. Paste the **Google Client ID** and **Google Client Secret**
2. Click **Save**

## 🚀 Step 6: Run the Application

1. Start the development server:
```bash
npm run dev
```

2. Open your browser and go to `http://localhost:5173`

## 🧪 Step 7: Testing Authentication

### Test Email Authentication

1. Click **Sign In** in the header
2. Click **Sign up here** to create a new account
3. Fill in the form with:
   - Your name
   - A valid email address
   - A password (minimum 6 characters)
4. Click **Create Account**
5. Check your email for verification (if enabled)
6. Try signing in with your credentials

### Test Google OAuth

1. Go to the Sign In page
2. Click **Continue with Google**
3. You should be redirected to Google's OAuth consent screen
4. Choose your Google account
5. You should be redirected back to your app and logged in

### Test Protected Routes

1. While signed out, try to access `/cartpage` or `/profile`
2. You should be redirected to the sign-in page
3. After signing in, you should be redirected back to the protected page

### Test User Display

1. After signing in, you should see your name in the header instead of "Sign In"
2. Click on your name to see the user menu
3. Try signing out from the dropdown menu

## 🔍 Testing Features

### ✅ What to Test

- [ ] Email registration works
- [ ] Email sign-in works
- [ ] Google OAuth works
- [ ] User name displays correctly in header
- [ ] User menu dropdown works
- [ ] Sign out works
- [ ] Protected routes redirect to sign-in
- [ ] After sign-in, redirects back to intended page
- [ ] Dark/light mode toggle works
- [ ] Mobile menu works
- [ ] Mobile authentication works

### 🐛 Common Issues and Solutions

#### 1. "Missing Supabase environment variables"
- Make sure `.env.local` exists and has the correct values
- Restart your dev server after changing environment variables

#### 2. Google OAuth redirects to wrong URL
- Check your Google Cloud Console redirect URIs
- Make sure they match exactly (including http vs https)

#### 3. Email confirmations not working
- Check your Supabase email settings
- For testing, you can disable email confirmations
- Check spam folder for confirmation emails

#### 4. "Invalid redirect URL" on Google OAuth
- Verify your Google OAuth redirect URIs include both:
  - `https://your-project.supabase.co/auth/v1/callback`
  - `http://localhost:5173/auth/callback`

#### 5. User data not displaying
- Check browser console for errors
- Verify your Supabase RLS (Row Level Security) policies if you have them enabled

## 📱 Mobile Testing

1. Open Chrome DevTools (F12)
2. Click the device toggle icon (Ctrl+Shift+M)
3. Select a mobile device
4. Test all authentication flows on mobile

## 🚀 Production Deployment

Before deploying to production:

1. Update environment variables:
```env
VITE_SUPABASE_URL=https://your-project.supabase.co
VITE_SUPABASE_ANON_KEY=your_supabase_anon_key_here
VITE_SITE_URL=https://your-production-domain.com
```

2. Update Google OAuth redirect URIs to include your production domain

3. Enable email confirmations in Supabase for security

4. Test all flows in production environment

## 📧 Support

If you encounter issues:

1. Check the browser console for errors
2. Check Supabase logs in the dashboard
3. Verify all environment variables are correct
4. Ensure Supabase project is running and not paused

## 🎉 Success!

If everything is working correctly, you now have:
- ✅ Email authentication with registration and sign-in
- ✅ Google OAuth integration
- ✅ Protected routes
- ✅ User state management
- ✅ Responsive design with dark/light mode
- ✅ Beautiful UI with proper error handling

Your authentication system is now ready for development and production use!