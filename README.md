# TrekPass2 - Mountain Adventure Booking App

A comprehensive Android application for booking mountain adventures with Firebase integration for authentication and database management.

## Features

### 🏔️ **Complete User Journey**
- **Starting Page**: Beautiful Trek Pass card with call-to-action
- **Authentication**: Firebase-powered login and signup
- **Discovery**: Browse mountain adventures with ratings
- **Details**: View detailed information and book adventures
- **Payment**: Secure payment processing with Firebase database storage
- **Profile**: User profile management

### 🔥 **Firebase Integration**
- **Authentication**: Email/password login and registration
- **Database**: Real-time data storage for bookings and user information
- **User Management**: Secure user sessions and data persistence

### 🎨 **UI/UX Design**
- Modern, clean interface matching the provided designs
- Material Design components
- Responsive layouts
- Intuitive navigation flow

## App Flow

1. **Starting Page** → User sees Trek Pass card
2. **Login/Signup** → Authentication via Firebase
3. **Discover Page** → Browse mountain adventures
4. **Detail Page** → View details and select quantity
5. **Payment Page** → Complete booking with Firebase storage
6. **Profile Page** → Manage user information

## Technical Stack

- **Language**: Kotlin
- **UI Framework**: Android Views with Material Design
- **Authentication**: Firebase Auth
- **Database**: Firebase Realtime Database
- **Architecture**: Activity-based navigation

## Project Structure

```
app/src/main/
├── java/com/example/trekpass2/
│   ├── StartingActivity.kt      # Welcome screen with Trek Pass card
│   ├── LoginActivity.kt         # User login with Firebase Auth
│   ├── SignupActivity.kt        # User registration with Firebase
│   ├── DiscoverActivity.kt      # Mountain browsing interface
│   ├── DetailActivity.kt        # Mountain details and booking
│   ├── PaymentActivity.kt       # Payment processing
│   ├── ProfileActivity.kt       # User profile management
│   └── MainActivity.kt          # Original template activity
├── res/
│   ├── layout/                  # UI layouts for all screens
│   ├── drawable/                # Icons and graphics
│   ├── values/
│   │   ├── colors.xml           # App color scheme
│   │   └── strings.xml          # String resources
│   └── ...
└── AndroidManifest.xml          # App configuration
```

## Firebase Setup

### 1. Authentication
- Email/Password authentication enabled
- User registration with profile data storage
- Secure session management

### 2. Database Structure
```
Firebase Database:
├── users/
│   └── {userId}/
│       ├── fullName: String
│       ├── email: String
│       └── uid: String
└── bookings/
    └── {bookingId}/
        ├── userId: String
        ├── title: String
        ├── quantity: Number
        ├── totalPrice: Number
        ├── paymentMethod: String
        ├── smartCard: String
        ├── amount: String
        ├── currency: String
        ├── bookingDate: String
        └── status: String
```

## Key Features

### 🔐 **Authentication System**
- Secure user registration and login
- Form validation and error handling
- User data persistence in Firebase

### 🏔️ **Adventure Discovery**
- Interactive mountain cards with ratings
- Star rating system
- Heart favorite functionality
- Detailed information display

### 💳 **Booking System**
- Quantity selection with +/- controls
- Dynamic pricing calculation
- Payment method selection
- Smart card integration
- Currency support

### 📱 **Navigation**
- Intuitive bottom navigation
- Back button functionality
- Seamless screen transitions
- Profile access from discovery

## Color Scheme

- **Light Purple**: `#D8CFF7` - Card backgrounds
- **Medium Purple**: `#7B68EE` - Primary buttons and accents
- **Dark Purple**: `#4A4A4A` - Text and headers
- **Light Gray**: `#F5F5F5` - Background
- **Text Gray**: `#666666` - Secondary text
- **Light Blue**: `#4A90E2` - Links and highlights
- **Star Yellow**: `#FFD700` - Rating stars

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Firebase project setup
- Android device/emulator (API 24+)

### Installation
1. Clone the repository
2. Open in Android Studio
3. Add your `google-services.json` file to the `app/` directory
4. Enable Firebase Authentication and Realtime Database
5. Build and run the project

### Firebase Configuration
1. Create a new Firebase project
2. Add Android app with package name: `com.example.trekpass2`
3. Download `google-services.json` and place in `app/` directory
4. Enable Authentication → Sign-in method → Email/Password
5. Enable Realtime Database

## Usage

1. **Launch App**: Start with the beautiful Trek Pass welcome screen
2. **Sign Up**: Create account or login with existing credentials
3. **Discover**: Browse available mountain adventures
4. **Select**: Tap on any mountain card to view details
5. **Book**: Choose quantity and proceed to payment
6. **Pay**: Complete payment with booking confirmation
7. **Profile**: Access user profile and settings

## Future Enhancements

- Real mountain images and data
- Advanced search and filtering
- Booking history and management
- Push notifications
- Offline support
- Social features and reviews
- Multiple payment methods
- Admin dashboard

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please create an issue in the repository or contact the development team.

---

**Built with ❤️ for mountain adventure enthusiasts**
