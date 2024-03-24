package com.onyshkiv.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp);
}
