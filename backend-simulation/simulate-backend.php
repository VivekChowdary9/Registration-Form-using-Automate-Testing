<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST, GET, OPTIONS');
header('Access-Control-Allow-Headers: Content-Type');

// Handle preflight requests
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    http_response_code(200);
    exit();
}

// Simulate backend processing with delay
sleep(2);

// Get POST data
$input = json_decode(file_get_contents('php://input'), true);

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Validate required fields
    $requiredFields = ['firstName', 'lastName', 'email', 'phone', 'age', 'gender', 'address', 'country', 'state', 'city', 'password'];
    
    $missingFields = [];
    foreach ($requiredFields as $field) {
        if (empty($input[$field])) {
            $missingFields[] = $field;
        }
    }
    
    if (!empty($missingFields)) {
        echo json_encode([
            'success' => false,
            'message' => 'Missing required fields: ' . implode(', ', $missingFields),
            'timestamp' => date('Y-m-d H:i:s')
        ]);
        exit;
    }
    
    // Validate email
    if (!filter_var($input['email'], FILTER_VALIDATE_EMAIL)) {
        echo json_encode([
            'success' => false,
            'message' => 'Invalid email address',
            'timestamp' => date('Y-m-d H:i:s')
        ]);
        exit;
    }
    
    // Check for disposable email
    $disposableDomains = ['tempmail.com', 'mailinator.com', '10minutemail.com'];
    $emailDomain = explode('@', $input['email'])[1];
    if (in_array($emailDomain, $disposableDomains)) {
        echo json_encode([
            'success' => false,
            'message' => 'Disposable email addresses are not allowed',
            'timestamp' => date('Y-m-d H:i:s')
        ]);
        exit;
    }
    
    // Validate password strength
    $password = $input['password'];
    $strength = 0;
    if (strlen($password) >= 8) $strength++;
    if (preg_match('/[A-Z]/', $password)) $strength++;
    if (preg_match('/[a-z]/', $password)) $strength++;
    if (preg_match('/[0-9]/', $password)) $strength++;
    if (preg_match('/[^A-Za-z0-9]/', $password)) $strength++;
    
    if ($strength < 3) {
        echo json_encode([
            'success' => false,
            'message' => 'Password is too weak',
            'timestamp' => date('Y-m-d H:i:s')
        ]);
        exit;
    }
    
    // Simulate successful registration
    $userId = uniqid('user_', true);
    
    echo json_encode([
        'success' => true,
        'message' => 'Registration successful!',
        'data' => [
            'userId' => $userId,
            'firstName' => $input['firstName'],
            'lastName' => $input['lastName'],
            'email' => $input['email'],
            'registrationDate' => date('Y-m-d H:i:s'),
            'strength' => $strength
        ],
        'timestamp' => date('Y-m-d H:i:s')
    ]);
    
} else {
    echo json_encode([
        'success' => false,
        'message' => 'Method not allowed',
        'timestamp' => date('Y-m-d H:i:s')
    ]);
}
?>