# QC Site Manager Android project

This is a starter Android Studio project based on the provided MainActivity.java, with a SQLite database helper and an automated GitHub Actions APK build workflow.

## Build APK in the cloud
1. Upload this project folder's contents to a new GitHub repository.
2. Open the repository's **Actions** tab and enable workflows if asked.
3. Run **Build Android APK** (or push a commit to `main`/`master`).
4. Open the completed workflow run and download the `QC-Site-Manager-debug-apk` artifact.
5. Extract the downloaded ZIP on your Android phone, open the `.apk`, and install it. Android may ask you to allow installs from that app/source.

## Important limitations
- This is a starter build, not a full production release. It has offline registers for SORs, inspections, cube tests, documents, and CSV export.
- Attachments are stored as URI references; the current UI does not copy image files into app storage or render them in reports.
- There is no cloud sync, login/roles, PDF report generation, MAA Portal integration, or automatic acceptance assessment yet.
- Before project use, test the app and verify records/backups. Do not use it as a substitute for approved project procedures.
