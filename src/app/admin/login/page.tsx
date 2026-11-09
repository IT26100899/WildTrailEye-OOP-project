import { redirect } from "next/navigation";
import { AuthPanel } from "@/components/client/AuthPanel";
import AdminLoginForm from "@/components/admin/AdminLoginForm";
import { getAdminSession } from "@/lib/session";

export const metadata = { title: "Admin sign in — WildTrail" };

export default async function AdminLoginPage() {
  if (await getAdminSession()) redirect("/admin/dashboard");

  return (
    <AuthPanel title="Admin sign in" subtitle="Sign in with an administrator account to access the dashboard.">
      <AdminLoginForm />
    </AuthPanel>
  );
}
