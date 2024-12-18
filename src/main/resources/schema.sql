CREATE TABLE IF NOT EXISTS musical_pieces (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    pdf_file_path TEXT,
    saved_annotations TEXT
);

CREATE TABLE IF NOT EXISTS practice_sessions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    duration INTEGER NOT NULL,
    date_time TIMESTAMP NOT NULL,
    notes TEXT,
    musical_piece_id INTEGER NOT NULL,
    FOREIGN KEY (musical_piece_id) REFERENCES musical_pieces(id)
);

CREATE TABLE IF NOT EXISTS recordings (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    file_path TEXT NOT NULL,
    date_recorded TIMESTAMP NOT NULL,
    notes TEXT,
    musical_piece_id INTEGER NOT NULL,
    FOREIGN KEY (musical_piece_id) REFERENCES musical_pieces(id)
);

CREATE TABLE IF NOT EXISTS reference_recordings (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    spotify_track_id TEXT NOT NULL,
    artist_name TEXT NOT NULL,
    recording_title TEXT NOT NULL,
    notes TEXT,
    musical_piece_id INTEGER NOT NULL,
    FOREIGN KEY (musical_piece_id) REFERENCES musical_pieces(id)
);