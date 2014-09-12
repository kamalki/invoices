class AddReservationIdColumnToCustomer < ActiveRecord::Migration
  def change
    add_column :customers, :reservation_id, :integer
  end
end
