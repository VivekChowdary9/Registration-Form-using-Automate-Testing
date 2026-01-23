// Extended validation rules for the registration form

class ValidationRules {
    constructor() {
        this.disposableDomains = [
            'tempmail.com', 'mailinator.com', 'guerrillamail.com',
            '10minutemail.com', 'yopmail.com', 'trashmail.com',
            'fakeinbox.com', 'throwawaymail.com', 'temp-mail.org',
            'sharklasers.com', 'grr.la', 'guerrillamail.biz'
        ];
        
        this.countryPhoneCodes = {
            'usa': '+1',
            'india': '+91',
            'uk': '+44',
            'canada': '+1',
            'australia': '+61',
            'germany': '+49'
        };
    }
    
    // Validate email against disposable domains
    validateEmail(email) {
        if (!email) return { isValid: false, message: 'Email is required' };
        
        // Basic email format validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            return { isValid: false, message: 'Invalid email format' };
        }
        
        // Check for disposable domains
        const domain = email.split('@')[1].toLowerCase();
        if (this.disposableDomains.some(disposable => domain.endsWith(disposable))) {
            return { isValid: false, message: 'Disposable email addresses are not allowed' };
        }
        
        return { isValid: true, message: '' };
    }
    
    // Validate phone number with country code
    validatePhone(phone, country) {
        if (!phone) return { isValid: false, message: 'Phone number is required' };
        
        // Remove all non-digit characters
        const cleanPhone = phone.replace(/\D/g, '');
        
        // Validate based on country
        switch(country) {
            case 'india':
                if (cleanPhone.length !== 10) {
                    return { isValid: false, message: 'Indian phone numbers must be 10 digits' };
                }
                break;
            case 'usa':
            case 'canada':
                if (cleanPhone.length !== 10) {
                    return { isValid: false, message: 'US/Canada phone numbers must be 10 digits' };
                }
                break;
            case 'uk':
                if (cleanPhone.length !== 10 && cleanPhone.length !== 11) {
                    return { isValid: false, message: 'UK phone numbers must be 10-11 digits' };
                }
                break;
            default:
                if (cleanPhone.length < 8 || cleanPhone.length > 15) {
                    return { isValid: false, message: 'Phone number must be 8-15 digits' };
                }
        }
        
        return { isValid: true, message: '' };
    }
    
    // Validate password strength
    validatePassword(password) {
        if (!password) return { isValid: false, message: 'Password is required', strength: 'weak' };
        
        const strength = this.checkPasswordStrength(password);
        let message = '';
        
        if (strength === 'weak') {
            message = 'Password is too weak. Use at least 8 characters with mix of uppercase, lowercase, numbers and special characters';
        } else if (strength === 'medium') {
            message = 'Password strength: Medium. Consider adding more complexity';
        } else {
            message = 'Password strength: Strong';
        }
        
        return {
            isValid: strength !== 'weak',
            message: message,
            strength: strength
        };
    }
    
    // Check password strength
    checkPasswordStrength(password) {
        let score = 0;
        
        // Length check
        if (password.length >= 8) score++;
        if (password.length >= 12) score++;
        
        // Character variety checks
        if (/[a-z]/.test(password)) score++;
        if (/[A-Z]/.test(password)) score++;
        if (/[0-9]/.test(password)) score++;
        if (/[^a-zA-Z0-9]/.test(password)) score++;
        
        // Determine strength
        if (score <= 2) return 'weak';
        if (score <= 4) return 'medium';
        return 'strong';
    }
    
    // Validate age
    validateAge(age) {
        const ageNum = parseInt(age);
        if (isNaN(ageNum)) {
            return { isValid: false, message: 'Age must be a number' };
        }
        
        if (ageNum < 18) {
            return { isValid: false, message: 'You must be at least 18 years old' };
        }
        
        if (ageNum > 100) {
            return { isValid: false, message: 'Please enter a valid age' };
        }
        
        return { isValid: true, message: '' };
    }
    
    // Validate name
    validateName(name, fieldName) {
        if (!name) {
            return { isValid: false, message: `${fieldName} is required` };
        }
        
        if (name.length < 2) {
            return { isValid: false, message: `${fieldName} must be at least 2 characters` };
        }
        
        if (!/^[a-zA-Z\s]+$/.test(name)) {
            return { isValid: false, message: `${fieldName} can only contain letters and spaces` };
        }
        
        return { isValid: true, message: '' };
    }
    
    // Validate address
    validateAddress(address) {
        if (!address) {
            return { isValid: false, message: 'Address is required' };
        }
        
        if (address.length < 10) {
            return { isValid: false, message: 'Address must be at least 10 characters' };
        }
        
        return { isValid: true, message: '' };
    }
    
    // Validate dropdown selection
    validateDropdown(value, fieldName) {
        if (!value) {
            return { isValid: false, message: `Please select a ${fieldName}` };
        }
        
        return { isValid: true, message: '' };
    }
    
    // Validate checkbox (terms and conditions)
    validateCheckbox(isChecked, fieldName) {
        if (!isChecked) {
            return { isValid: false, message: `You must accept the ${fieldName}` };
        }
        
        return { isValid: true, message: '' };
    }
    
    // Validate password match
    validatePasswordMatch(password, confirmPassword) {
        if (password !== confirmPassword) {
            return { isValid: false, message: 'Passwords do not match' };
        }
        
        return { isValid: true, message: '' };
    }
}

// Create global instance
const validationRules = new ValidationRules();

// Export for use in other files
if (typeof module !== 'undefined' && module.exports) {
    module.exports = validationRules;
}