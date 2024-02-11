import React, { useState } from 'react';

function LinkForm({ onSubmit, onInputChange, inputValue }) {
    return (
        <form onSubmit={onSubmit}>
            <input
                type="text"
                value={inputValue}
                onChange={onInputChange}
                placeholder="Enter URL or HTML"
                required
            />
            <button type="submit">Check Links</button>
        </form>
    );
}

export default LinkForm;
