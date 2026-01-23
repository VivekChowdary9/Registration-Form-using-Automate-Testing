document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registrationForm');
    const submitBtn = document.getElementById('submitBtn');
    const resetBtn = document.getElementById('resetBtn');
    const formAlert = document.getElementById('formAlert');
    
    // Real-time validation
    const inputs = form.querySelectorAll('input, select, textarea');
    inputs.forEach(input => {
        input.addEventListener('input', validateForm);
        input.addEventListener('change', validateForm);
    });
    
    // Gender validation
    const genderRadios = document.querySelectorAll('input[name="gender"]');
    genderRadios.forEach(radio => {
        radio.addEventListener('change', validateForm);
    });
    
    // Terms checkbox
    document.getElementById('terms').addEventListener('change', validateForm);
    
    // Form submission
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        if (validateForm()) {
            // Show loading state
            submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Creating Account...';
            submitBtn.disabled = true;
            
            // Simulate API call
            setTimeout(() => {
                // Show success message
                formAlert.innerHTML = `
                    <div style="display: flex; align-items: center; gap: 10px;">
                        <i class="fas fa-check-circle"></i>
                        <div>
                            <strong>Registration Successful!</strong>
                            <p style="margin: 5px 0 0 0; font-size: 14px;">Your account has been created successfully.</p>
                        </div>
                    </div>
                `;
                formAlert.className = 'alert-box success';
                
                // Reset button
                submitBtn.innerHTML = '<i class="fas fa-paper-plane"></i> Create Account';
                
                // Reset form after 3 seconds
                setTimeout(() => {
                    resetForm();
                    formAlert.style.display = 'none';
                }, 3000);
            }, 2000);
        } else {
            // Show error message
            formAlert.innerHTML = `
                <div style="display: flex; align-items: center; gap: 10px;">
                    <i class="fas fa-exclamation-circle"></i>
                    <div>
                        <strong>Please fix all errors</strong>
                        <p style="margin: 5px 0 0 0; font-size: 14px;">All required fields must be filled correctly.</p>
                    </div>
                </div>
            `;
            formAlert.className = 'alert-box error';
        }
    });
    
    // Reset form
    resetBtn.addEventListener('click', resetForm);
    
    function validateForm() {
        let isValid = true;
        
        // Check all required fields
        const requiredFields = form.querySelectorAll('[required]');
        requiredFields.forEach(field => {
            if (field.type === 'radio') {
                const name = field.name;
                const isChecked = form.querySelectorAll(`input[name="${name}"]:checked`).length > 0;
                if (!isChecked) {
                    document.getElementById(name + 'Error').textContent = 'This field is required';
                    isValid = false;
                } else {
                    document.getElementById(name + 'Error').textContent = '';
                }
            } else if (field.type === 'checkbox') {
                if (!field.checked) {
                    document.getElementById(field.name + 'Error').textContent = 'You must accept the terms';
                    isValid = false;
                } else {
                    document.getElementById(field.name + 'Error').textContent = '';
                }
            } else {
                if (!field.value.trim()) {
                    document.getElementById(field.id + 'Error').textContent = 'This field is required';
                    field.classList.add('error');
                    isValid = false;
                } else {
                    document.getElementById(field.id + 'Error').textContent = '';
                    field.classList.remove('error');
                }
            }
        });
        
        // Validate email
        const email = document.getElementById('email').value;
        if (email && !isValidEmail(email)) {
            document.getElementById('emailError').textContent = 'Please enter a valid email';
            isValid = false;
        }
        
        // Validate password match
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        if (password && confirmPassword && password !== confirmPassword) {
            document.getElementById('confirmPasswordError').textContent = 'Passwords do not match';
            isValid = false;
        }
        
        // Update submit button
        submitBtn.disabled = !isValid;
        
        return isValid;
    }
    
    function resetForm() {
        form.reset();
        document.querySelectorAll('.error-message').forEach(el => {
            el.textContent = '';
        });
        document.querySelectorAll('.error').forEach(el => {
            el.classList.remove('error');
        });
        submitBtn.disabled = true;
        formAlert.style.display = 'none';
    }
    
    function isValidEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }
});